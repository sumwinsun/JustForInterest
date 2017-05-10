/* jshint esversion: 6 */
((main) => {
	
	main(this, document, Math, {
		v2: Vector2,
		noise: noise
	});

})((window, document, Math, lib, undefined) => {

	'use strict';

	const PI = Math.PI,
		TAU = PI * 2,
		ABS = Math.abs,
		RAND = Math.random,
		ROUND = Math.round,
		SIN = Math.sin,
		COS = Math.cos;
	
	class Config {
		constructor(opts) {
			for (let opt in opts) {
				this.set(opt, opts[opt]);
			}
		}
		set(key, value) {
			if (!key || !value) 
				return;
			else
				this[key] = value;
		}
	}
	
	class Canvas {
		constructor(selector, context, dimensions) {
			let self = this;
			
			window.requestAnimationFrame = (() => {
				return window.requestAnimationFrame ||
					window.webkitRequestAnimationFrame ||
					window.mozRequestAnimationFrame ||
					window.oRequestAnimationFrame ||
					window.msRequestAnimationFrame ||
					function(callback) {
						window.setTimeout(callback, 1000 / 60);
					};
			})();
			
			if (selector) {
				this.el = document.querySelector(selector);
			} else {
				this.el = document.createElement('canvas');
				document.body.appendChild(this.el);
			}
			this.ctx = this.el.getContext(context) || this.el.getContext('2d');
			this.dimensions = dimensions || { x: 0, y: 0 };
			this.resize();
			window.addEventListener('resize', self.resize.bind(self));
		}
		hsla(h, s, l, a) {
			return 'hsla(' + h + ',' + s + ',' + l + ',' + a + ')';
		}
		fill(x, y, width, height, fill) {
			this.ctx.fillStyle = fill || 'rgba(0,0,0,1)';
			this.ctx.fillRect(x, y, width, height);
		}
		drawLine(x1, y1, x2, y2, stroke, strokeWidth) {
			this.ctx.beginPath();
			this.ctx.moveTo(x1, y1);
			this.ctx.lineTo(x2, y2);
			this.ctx.strokeStyle = stroke || 'rgba(255,255,255,1)';
			this.ctx.lineWidth = strokeWidth || '2';
			this.ctx.stroke();
			this.ctx.closePath();
		}
		drawArc(x, y, r, fill, stroke, strokeWidth) {
			this.ctx.beginPath();
			this.ctx.arc(x, y, r, 0, TAU);
			this.ctx.fillStyle = fill || 'rgba(200,0,0,1)';
			this.ctx.fill();
			if (stroke) {
				this.ctx.strokeStyle = stroke;
				this.ctx.lineWidth = strokeWidth || '2';
				this.ctx.stroke();
			}
			this.ctx.closePath();
		}
		resize() {
			this.el.width = this.dimensions.x = window.innerWidth;
			this.el.height = this.dimensions.y = window.innerHeight;
			this.center = { x: this.dimensions.x * 0.5, y: this.dimensions.y * 0.5 };
		}
	}
	
	class Particle {
		constructor(x, y) {
			this.lastPosition = { x:0, y:0 };
			this.position = new lib.v2(x, y);
			this.velocity = new lib.v2();
		}
	}
	
	class Attractor {
		constructor(x, y) {
			this.size = 0;
			this.threshold = 0;
			this.position = new lib.v2(x, y);
		}
	}
	
	class Mouse {
		constructor() {
			let self = this,
				evts = ['mouseenter', 'mousemove', 'mouseout', 'mousedown', 'mouseup', 'dblclick'];
			this.hover = false;
			this.mousedown = false;
			this.dblClick = false;
			this.position = new lib.v2();
			for (let evt of evts) {
				window.addEventListener(evt, self.handler.bind(self));
			}
		}
		handler(e) {
			switch (e.type) {
				case 'mousedown':
					this.mousedown = true;
					break;
				case 'mouseup':
					this.mousedown = false;
					break;
				case 'mouseenter':
					this.hover = true;
					break;
				case 'mousemove':
					this.hover = true;
					break;
				case 'mouseout':
					this.hover = false;
					break;
				case 'dblclick':
					this.dblclick = true;
					break;
				default:
					break;
			}
			if (this.hover) {
				this.position.x = e.clientX;
				this.position.y = e.clientY;
			}
		}
	}
	
	class ParticleSystem {
		constructor() {
			this.tick = 0;
			this.particles = [];
			this.attractors = [];
			this.mouse = new Mouse();
			this.canvas = new Canvas('.canvas', '2d');
			this.bounds = this.canvas.dimensions;
			this.countDisplay = document.querySelector('.count');
			this.config = new Config({
				fill: 'rgba(0,0,0,0.2)',
				size: 2,
				speed: {
					x: 8,
					y: 8
				},
				maxCount: 2000,
				mouseThreshold: 200,
				mousePower: 30,
				attractorThreshold: 300,
				attractorPower: 30,
				attractorSize: 12,
				attractorColor: 'rgba(10,10,10,0.4)',
				maxAttractors: 12
			});
			this.render();
		}
		render() {
			let self = this;
			self.update();
			if(self.tick % 10 === 0)
				self.countDisplay.innerHTML = this.particles.length.toString() + ' Particles';
			window.requestAnimationFrame(self.render.bind(self));
		}
		update() {
			let i, j, p, noiseVal, noiseNorm, theta, vel, hue, colorString;
			if (this.mouse.dblclick) {
				let remove = false;
				for (j = this.attractors.length - 1; j >= 0; j--) {
					let a = this.attractors[j];
					if (remove) {
						break;
					} else if (this.mouse.position.distanceTo(a.position) < a.size) {
						this.attractors.splice(j, 1);
						remove = true;
					}
				}
				if (!remove && this.attractors.length < this.config.maxAttractors) {
					this.attractors.push(new Attractor(this.mouse.position.x, this.mouse.position.y));
				}
				this.mouse.dblclick = false;
			}
			this.canvas.fill(0, 0, this.canvas.dimensions.x, this.canvas.dimensions.y, this.config.fill);
			for (i = 3; i > 0; i--){
				p = new Particle(RAND() * this.canvas.dimensions.x, RAND() * this.canvas.dimensions.y);
				this.particles.push(p);
			}
			for (i = this.particles.length - 1; i >= 0; i--) {
				p = this.particles[i];
				this.checkBounds(p);
				if (p.remove === true || this.particles.length > this.config.maxCount) {
					this.particles.splice(i, 1);
				} else {
					p.lastPosition.x = p.position.x;
					p.lastPosition.y = p.position.y;
					noiseVal = lib.noise.simplex3(
						p.position.x * 0.0015 * (this.mouse.position.x / this.bounds.x + 0.1),
						p.position.y * 0.0025 * (this.mouse.position.y / this.bounds.y + 0.1), 
						this.tick * 0.0015
					);
					noiseNorm = ABS(noiseVal);
					theta = noiseVal * TAU;
					vel = new lib.v2(COS(theta) * this.config.speed.x, SIN(theta) * this.config.speed.y);
					hue = 90 * noiseNorm + 160;
					colorString = this.canvas.hsla(hue, '100%', '60%', 1);
					p.velocity.lerp(vel, 0.0125);
					p.position.add(p.velocity);
					if (this.mouse.hover && this.mouse.position.distanceTo(p.position) < this.config.mouseThreshold) {
						if (this.mouse.mousedown && this.config.mousePower > 0) {
							this.config.mousePower *= -1;
						} else if (!this.mouse.mousedown && this.config.mousePower < 0) {
							this.config.mousePower = ABS(this.config.mousePower);
						}
						if (this.mouse.position.distanceTo(p.position) < 1) {
							this.particles.splice(i, 1);
						} else {
							let mTheta = this.mouse.position.angleTo(p.position),
								mVel = new lib.v2(COS(mTheta) * this.config.mousePower, SIN(mTheta) * this.config.mousePower);
							p.velocity.lerp(mVel, 0.0125);
						}
					}
					for (j = this.attractors.length - 1; j >= 0; j--) {
						let a = this.attractors[j],
							pDist = p.position.distanceTo(a.position);
						if (pDist < a.threshold) {
							if (pDist < 2) {
								this.particles.splice(i, 1);
								continue;
							} else {
								let aTheta = p.position.angleTo(a.position),
									aVel = new lib.v2(COS(aTheta) * this.config.attractorPower, SIN(aTheta) * this.config.attractorPower);
								p.velocity.lerp(aVel, 0.0075);
							}
						}
					}
					this.canvas.ctx.save();
					this.canvas.ctx.globalCompositeOperation = 'lighter';
					this.canvas.drawLine(
						p.position.x, p.position.y, 
						p.lastPosition.x, p.lastPosition.y, 
						colorString, this.config.size
					);
					this.canvas.ctx.restore();
				}
			}
			for (j = this.attractors.length - 1; j >= 0; j--) {
				let a = this.attractors[j];
				if (a.size < this.config.attractorSize) a.size += 0.15;
				if (a.threshold < this.config.attractorThreshold) a.threshold += 0.9;
				this.canvas.ctx.save();
				this.canvas.ctx.shadowBlur = RAND() * 18 + 4;
				this.canvas.ctx.shadowColor = colorString;
				this.canvas.drawArc(a.position.x, a.position.y, a.size, this.config.attractorColor);
				this.canvas.ctx.restore();
			}
			this.tick++;
		}
		checkBounds(p) {
			if (p.position.x > this.bounds.x || p.position.x < -this.config.size || p.position.y > this.bounds.y || p.position.y < -this.config.size) {
				p.remove = true;
			}
		}
	}

	window.onload = () => {
		lib.noise.seed(RAND() * 2000);
		let flow = new ParticleSystem();
	};

});
/**
 * Created by Administrator on 2017/1/16 0016.
 */
/*变量定义*/
var canvasWidth = window.innerWidth;
var canvasHeight = window.innerHeight;
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
// var rightArea = ;
/*半径*/
var radius = 50;
/*定义用于存储小鸡数据的数组*/
var chicken = [];

canvas.width = canvasWidth;
canvas.height = canvasHeight;

var image = new Image();
var clippingRegion = {x: -1, y: -1, r: radius};
image.src = "xx.jpg";
image.onload = function (e) {
    initCanvas();
}
function initCanvas() {
    clippingRegion = {
        x: Math.random() * (canvasWidth - 2 * radius) + radius,
        y: Math.random() * (canvasHeight - 2 * radius) + radius,
        r: radius
    };
    draw(image, clippingRegion);
}
function setClippingRegion(_clippingRegion) {
    context.beginPath();
    context.arc(_clippingRegion.x, _clippingRegion.y, _clippingRegion.r, 0, 2 * Math.PI, false);
    context.clip();
}
function draw(image, _clippingRegion) {
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.save();
    /*设置剪辑路径*/
    setClippingRegion(_clippingRegion);
    context.drawImage(image, _clippingRegion.x - radius, _clippingRegion.y - radius);
    context.restore();
}
/*初始化函数*/
function initData() {
    setInterval(
        function () {
            initCanvas();
        }, 1000
    )
}
/*Get Mouse Position*/
function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left * (canvas.width / rect.width),
        y: evt.clientY - rect.top * (canvas.height / rect.height)
    }
}
/*判断位置是否点击正确*/
function judgeClick(_mousePos) {
    if (Math.sqrt(Math.abs(_mousePos.x - clippingRegion.x) * Math.abs(_mousePos.x - clippingRegion.x) + Math.abs(_mousePos.y - clippingRegion.y) * Math.abs(_mousePos.y - clippingRegion.y)) <= radius) {
        //加分
        return true;
    }
}
canvas.addEventListener("click", function (evt) {
    var mousePos = getMousePos(canvas, evt);
    judgeClick(mousePos)
}, false);
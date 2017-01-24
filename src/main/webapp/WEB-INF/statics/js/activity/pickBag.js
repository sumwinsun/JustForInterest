/**
 * Created by Administrator on 2017/1/16 0016.
 */
/*变量定义*/
var canvasWidth = window.innerWidth;
var canvasHeight = window.innerHeight;
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
var score = 0;
var item_count = 5;//屏幕内可以击打物体的个数
var time = '15s';
/*半径*/
var radius = 30;
var gameInterval;
/*定义用于存储小鸡数据的数组*/
var chicken = [];

canvas.width = canvasWidth;
canvas.height = canvasHeight;

var image = new Image();
var clippingRegion = {x: -1, y: -1, r: radius};
var clippingRegionArray = new Array();
var hitClippingRegion = new Array();//击中时候的位置点
var hitAnimation = new Array();//敲击动画
var bgImage = new Image();
var leftMargin = 0, topMargin = 0;

image.onload = function (e) {
    $('.con').css('width', canvasWidth);
    $('.con').css('height', canvasHeight);
    $('.bg').css('width', bgImage.width);
    $('.bg').css('height', bgImage.height);
    leftMargin = (bgImage.width - canvasWidth)/2;
    topMargin = (bgImage.height - canvasHeight)/2;
    $('.bg').css('margin-left', '-' + leftMargin + 'px');
    $('.bg').css('margin-top', '-' + topMargin + 'px');
    // initCanvas();
}
function randomClippingRegion() {
    var flag;
    var randomRegion;
    while (true){
        flag = true;
        randomRegion= {
            x: Math.random() * (canvasWidth - 2 * radius) + radius,
            y: Math.random() * (canvasHeight - 2 * radius) + radius,
            r: radius
        };
        console.log('randomRegion'+randomRegion);
        for (var i =0 ; i< clippingRegionArray.length; i++) {
            if (Math.abs(randomRegion.x - clippingRegionArray[i].x) < 2*radius && Math.abs(randomRegion.y - clippingRegionArray[i].y) < 2*radius){
                flag = false;
                break;
            }
        }
        if (flag == true)
            break;
    }
    return randomRegion;
}
function initCanvas() {
    for (var i=0 ; i < item_count ; i++){
        clippingRegion = randomClippingRegion();
        clippingRegionArray.push(clippingRegion)
    }
    drawMany(image, clippingRegionArray);
}
function setClippingRegion(_clippingRegion) {
    context.beginPath();
    context.arc(_clippingRegion.x, _clippingRegion.y, _clippingRegion.r, 0, 2 * Math.PI, false);
    context.clip();

}
/*画单个小鸡*/
function draw(image, _clippingRegion) {
    // context.clearRect(0, 0, canvas.width, canvas.height);
    // context.save();
    // /*设置剪辑路径*/
    // setClippingRegion(_clippingRegion);
    // context.drawImage(image, _clippingRegion.x - radius, _clippingRegion.y - radius);
    // context.restore();
    context.beginPath();
    context.arc(_clippingRegion.x, _clippingRegion.y, _clippingRegion.r, 0, 2 * Math.PI, false);
    context.clip();
    context.drawImage(image, _clippingRegion.x - radius, _clippingRegion.y - radius);
    context.restore();
    context.save();
}
/*画一群小鸡*/
function drawMany(image, _clippingRegionArray) {
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.save();

    for (var i in _clippingRegionArray){
        context.beginPath();
        context.arc(_clippingRegionArray[i].x, _clippingRegionArray[i].y, _clippingRegionArray[i].r, 0, 2 * Math.PI, false);
        context.clip();
        context.drawImage(image, _clippingRegionArray[i].x - radius, _clippingRegionArray[i].y - radius);
        context.restore();
        context.save();
    }
}

function removeHitAnimation(index) {
    clearInterval(hitAnimation[index]);
}
/*初始化函数*/
function initData(imageSrc, bgImageSrc) {
    image.src = imageSrc;
    bgImage.src = bgImageSrc;
    // var time = $('#time_left').text();
    time = time.substring(0, time.length-1);
    initCanvas();
    // gameInterval = setInterval(
    //     function () {
    //         if (clippingRegionArray.length > 0) {
    //             //清空动画展示，防止又去重画敲击动画
    //             if (hitAnimation.length > 0)
    //                 hitAnimation.forEach(removeHitAnimation);
    //             clippingRegionArray = new Array();
    //             context.clearRect(0, 0, canvasWidth, canvasHeight);
    //             context.save();
    //         }
    //         initCanvas();
    //     }, 2000
    // )
    var timeInterval = setInterval(
        function () {
            time--;
            $('#time_left').text(time + 's')
            if (time == 0) {
                clearInterval(gameInterval);
                clearInterval(timeInterval);

                context.clearRect(0, 0, canvas.width, canvas.height);
                context.save();
                if (hitAnimation.length > 0)
                    hitAnimation.forEach(removeHitAnimation);

                alert("共抓住了"+$('#score').text()+'"只”鸡“祥');
            }
        },1000
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
/*展示击中效果动画*/
function showHitAnimation(_clippingRegion) {
    var _hitAnimation = setInterval(
        function () {
            _clippingRegion.r -= radius/5;
            context.clearRect(_clippingRegion.x - radius,_clippingRegion.y -radius, 2*radius,2*radius);
            draw(image, _clippingRegion);
            if (_clippingRegion.r <= 0) {
                clearInterval(_hitAnimation);
                if (time > 0) {
                    //产生新的
                    _clippingRegion = randomClippingRegion();
                    clippingRegionArray.push(_clippingRegion);
                    draw(image, _clippingRegion);
                }
            }
        },50
    );
    hitAnimation.push(_hitAnimation);
}
/*判断位置是否点击正确*/
function judgeClick(_mousePos) {
    for (var i=0; i< clippingRegionArray.length; i++) {
        if (Math.sqrt(Math.abs(_mousePos.x - clippingRegionArray[i].x) * Math.abs(_mousePos.x - clippingRegionArray[i].x) + Math.abs(_mousePos.y - clippingRegionArray[i].y) * Math.abs(_mousePos.y - clippingRegionArray[i].y)) <= radius) {
            score++;
            $('#score').text(score);
            showHitAnimation(clippingRegionArray[i]);
            clippingRegionArray.splice(i, 1);
            break;
        }
    }
}
canvas.addEventListener("click", function (evt) {
    var mousePos = getMousePos(canvas, evt);
    judgeClick(mousePos)
}, false);
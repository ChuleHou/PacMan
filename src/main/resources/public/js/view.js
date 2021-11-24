'use strict';

//app to draw polymorphic shapes on canvas
var app;


/**
 * Create an app that can draw shapes on a canvas. It also has a function to clear the canvas.
 * @param canvas  The canvas to draw shapes on.
 * @returns {{drawCircle: drawCircle, clear: clear}}, an object with functions to draw shapes and clear the canvas.
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");
    c.fillStyle = "black";
    c.fillRect(0, 0, canvas.width, canvas.height);

    let drawWall = function (startX, startY, endX, endY,color){
        c.fillStyle = "blue";
        c.fillRect(100,100,700,750);
        c.fillStyle = "black";
        c.fillRect(100,500,100,100);
        c.fillStyle = "black";
        c.fillRect(700,300,100,100);
        c.fillStyle = "black";
        c.fillRect(150,150,600,650);
        c.fillStyle = "blue";
        c.fillRect(300,300,300,250);
        c.fillStyle = "black";
        c.fillRect(350,350,200,150);
        c.fillStyle = "yellow";
        c.fillRect(400,300,100,50);
        c.fillStyle = "blue";
        c.fillRect(200,200,500,50);
        c.fillStyle = "blue";
        c.fillRect(200,200,50,500);
        c.fillStyle = "blue";
        c.fillRect(200,700,500,50);
        c.fillStyle = "blue";
        c.fillRect(200,600,500,50);

    }

    let drawImg = function (img,x,y,w,h) {
        var img1 = new Image();
        img1.src = "Pacman.png";
        img1.addEventListener('load',function () {
            c.save();
            c.translate(150,150);
            c.drawImage(img1, 0, 0,50,50);
            c.restore();
        })
        var img2 = new Image();
        img2.src = "Fruit.png";
        img2.addEventListener('load',function () {
            c.save();
            c.translate(250,150);
            c.drawImage(img2, 0, 0,50,50);
            c.restore();
        })
        var img3 = new Image();
        img3.src = "Ghost.png";
        img3.addEventListener('load',function () {
            c.save();
            c.translate(350,350);
            c.drawImage(img3, 0, 0,50,50);
            c.restore();
            c.save();
            c.translate(450,350);
            c.drawImage(img3, 0, 0,50,50);
            c.restore();
            c.save();
            c.translate(350,450);
            c.drawImage(img3, 0, 0,50,50);
            c.restore();
            c.save();
            c.translate(450,450);
            c.drawImage(img3, 0, 0,50,50);
            c.restore();
        })
        var img4 = new Image();
        img4.src = "coin.png";
        img4.addEventListener('load',function () {
            c.save();
            c.translate(165,250);
            c.drawImage(img4, 0, 0,20,20);
            c.restore();
            c.save();
            var counter = 0;
            for (y = 300;y < 800;y+=50){
                c.translate(165,y);
                if(counter == 4) {
                    counter = 0;
                    c.translate(-5,0);
                    c.drawImage(img4, 0, 0, 30, 30);
                }
                else
                    c.drawImage(img4, 0, 0,20,20);
                counter++;
                c.restore();
                c.save();
            }
            for (x = 215;x < 700;x+=50){
                c.translate(x,760);
                c.drawImage(img4, 0, 0,20,20);
                c.restore();
                c.save();
            }
            for (x = 350;x < 750;x+=50){
                c.translate(x,170);
                c.drawImage(img4, 0, 0,20,20);
                c.restore();
                c.save();
            }
            for (y = 220;y < 800;y+=50){
                c.translate(720,y);
                c.drawImage(img4, 0, 0,20,20);
                c.restore();
                c.save();
            }
        })
        // var img4 = new Image();
        // img4.src = "fish.png";
        // c.save();
        // c.translate(400,400);
        // c.drawImage(img4, 0, 0,50,50);
        // c.restore();
    }

    let setLife = function (life) {
        var img1 = new Image();
        img1.src = "heart.png";
        c.font = '26px Helvetica';
        c.textAlign = 'left';
        c.textBaseline = 'center';
        c.fillStyle = '#FFF';
        c.fillText('Life: ',20,60);
        img1.addEventListener('load',function () {
            c.save();
            c.translate(80,20);
            c.drawImage(img1, 0, 0,50,50);
            c.restore();
            c.save();
            c.translate(120,20);
            c.drawImage(img1, 0, 0,50,50);
            c.restore();
            c.save();
            c.translate(160,20);
            c.drawImage(img1, 0, 0,50,50);
            c.restore();
        })
    }

    let setScore = function (score) {
        c.font = '26px Helvetica';
        c.textAlign = 'left';
        c.textBaseline = 'center';
        c.fillStyle = '#FFF';
        c.fillText('Score: 0',230,60);
    }

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    return {
        drawWall:drawWall,
        drawImg:drawImg,
        setLife:setLife,
        setScore:setScore,
        clear: clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}

/**
 * When the window loads, get the app that can draw shapes on the canvas and setup the button click actions.
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();
    drawWall();
    drawImg();
    setLife();
    setScore();
    getInput();
    $("#btn-clear").click(clear);
    $("#btn-load").click(drawImg);
};

function drawWall() {
    app.drawWall();
}
function drawImg() {
    app.drawImg();
}

function setLife() {
    app.setLife(5);
}

function setScore(){
    app.setScore(0);
}

function getInput() {
    $('body').keydown(e => {
        // console.log(e);
        let keyDown =e.keyCode - 36;
        switch (keyDown) {
            case -4:
                console.log("space");
                break;
            case 1:
                console.log("left");
                break;
            case 2:
                console.log("up");
                break;
            case 3:
                console.log("right");
                break;
            case 4:
                console.log("down");
                break;
            default:
                console.log("invalid");
                break;
        }
    });
}

/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
}


/**
 * Clear the canvas
 */
function clear() {
    app.clear();
   $.post("/canvas/clear",function (data) {
   });
}

document.querySelector("#graph").addEventListener("click", function (event) {
    if (FIELD_R.value !== "") {
        const r = FIELD_R.value;
        const x = ((event.offsetX - 180) / (rSize * 2)) * r;
        const y = ((-event.offsetY + 180) / (rSize * 2)) * r;
        rc([
            {
                name: "x",
                value: x.toString()
            },
            {
                name: "y",
                value: y.toString()
            },
            {
                name: "r",
                value: r.toString()
            }
        ]);
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Ooops...',
            text: 'Check R',
        })
    }
});

const canvas = document.querySelector('canvas')
const ctx = canvas.getContext('2d')
const width = canvas.width
const height = canvas.height
const markWidth = 5
const arrowSize = 5
const rSize = 60
const figureColor = '#39f'

function drawGraph() {
    drawFigure()
    drawPane()
}

function drawPane() {
    ctx.beginPath()
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width, height / 2)
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(0, height / 2)
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2, height)
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2, 0)
    ctx.moveTo(width / 2 + rSize, height / 2 + markWidth)
    ctx.lineTo(width / 2 + rSize, height / 2 - markWidth)
    ctx.moveTo(width / 2 + rSize * 2, height / 2 + markWidth)
    ctx.lineTo(width / 2 + rSize * 2, height / 2 - markWidth)
    ctx.moveTo(width / 2 - rSize, height / 2 + markWidth)
    ctx.lineTo(width / 2 - rSize, height / 2 - markWidth)
    ctx.moveTo(width / 2 - rSize * 2, height / 2 + markWidth)
    ctx.lineTo(width / 2 - rSize * 2, height / 2 - markWidth)
    ctx.moveTo(width / 2 + markWidth, height / 2 + rSize)
    ctx.lineTo(width / 2 - markWidth, height / 2 + rSize)
    ctx.moveTo(width / 2 + markWidth, height / 2 + rSize * 2)
    ctx.lineTo(width / 2 - markWidth, height / 2 + rSize * 2)
    ctx.moveTo(width / 2 + markWidth, height / 2 - rSize)
    ctx.lineTo(width / 2 - markWidth, height / 2 - rSize)
    ctx.moveTo(width / 2 + markWidth, height / 2 - rSize * 2)
    ctx.lineTo(width / 2 - markWidth, height / 2 - rSize * 2)
    ctx.moveTo(width / 2, 0)
    ctx.lineTo(width / 2 + arrowSize, arrowSize)
    ctx.moveTo(width / 2, 0)
    ctx.lineTo(width / 2 - arrowSize, arrowSize)
    ctx.moveTo(width, height / 2)
    ctx.lineTo(width - arrowSize, height / 2 + arrowSize)
    ctx.moveTo(width, height / 2)
    ctx.lineTo(width - arrowSize, height / 2 - arrowSize)
    ctx.moveTo(width / 2, height / 2)
    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
    ctx.textAlign = 'center';
    ctx.fillText('-R/2', width / 2 - rSize, height * 8 / 17)
    ctx.fillText('-R', width / 2 - rSize * 2, height * 8 / 17)
    ctx.fillText('R/2', width / 2 + rSize, height * 8 / 17)
    ctx.fillText('R', width / 2 + rSize * 2, height * 8 / 17)
    ctx.textAlign = 'left'
    ctx.textBaseline = 'middle'
    ctx.fillText('-R/2', width * 9 / 17, width / 2 + rSize)
    ctx.fillText('-R', width * 9 / 17, width / 2 + rSize * 2)
    ctx.fillText('R/2', width * 9 / 17, width / 2 - rSize)
    ctx.fillText('R', width * 9 / 17, width / 2 - rSize * 2)
    ctx.font = '15px monospace'
    ctx.fillText('y', width * 9 / 17, arrowSize * 2)
    ctx.textAlign = 'center'
    ctx.textBaseline = 'bottom'
    ctx.fillText('x', width - arrowSize, height * 8 / 17)
    ctx.stroke()
    ctx.textAlign = 'center';
    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
}


function drawFigure() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
    ctx.textAlign = 'center'
    ctx.textBaseline = 'middle'
    ctx.beginPath()
    ctx.fillStyle = figureColor
    ctx.moveTo(width /2, height / 2);
    ctx.lineTo(width /2 - rSize * 2, height / 2);
    ctx.lineTo(width / 2, height /2 + rSize * 2);
    ctx.lineTo(width /2 + rSize, height/2 + rSize * 2);
    ctx.lineTo(width/2 + rSize, height / 2);
    ctx.lineTo(width / 2, height /2);
    ctx.fill();
    ctx.moveTo(width / 2 - rSize * 2, height /2 );
    ctx.lineTo(width / 2, height / 2);
    ctx.lineTo(width /2, height / 2 - rSize * 2);
    ctx.arc(width / 2, height / 2, rSize * 2, Math.PI, Math.PI * 3/ 2);
    ctx.fill();
}

function drawDots(x, y, r, result) {
    drawGraph();
    const realR = FIELD_R.value;
    for (let i = 0; i < result.length; i++) {
        let realX = ((x[i] / r[i]) * 120) + 180;
        if (realX > 180) {
            realX = 180 + (realX - 180) * (r[i] / realR);
        } else {
            realX = 180 - ((180 - realX) * (r[i] / realR));
        }
        let realY = ((-y[i] / r[i]) * 120) + 180;
        if (realY > 180) {
            realY = 180 + ((realY - 180) * (r[i] / realR));
        } else {
            realY = 180 - ((180 - realY) * (r[i] / realR));
        }
        if (result[i].toString() === 'true') {
            ctx.fillStyle = '#0f0';
        } else {
            ctx.fillStyle = '#F00';
        }
        ctx.beginPath();
        ctx.moveTo(realX, realY);
        ctx.arc(realX, realY, 3, 0, Math.PI * 2);
        ctx.fill();
    }
}

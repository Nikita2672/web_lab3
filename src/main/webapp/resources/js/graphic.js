function drawGraphics(date1, open1, high1, low1, close1) {
    // document.getElementById('graph').textContent = '';
    // let svgElement = document.createElement('svg');
    // svgElement.id = "boxplot";
    // document.getElementById('graph').append(svgElement);
    document.getElementById('boxplot').innerHTML = '';
    const date = date1;
    const open = open1;
    const high = high1;
    const low = low1;
    const close = close1;
    date.unshift(date1[0].getDate - 1);
    date.push(date[date.length-1].getDate + 1);
    date.push(date[date.length-1].getDate + 1);
    date.push(date[date.length-1].getDate + 1);
    const massDate = [];
    const massOpen = [];
    const massHigh = [];
    const massLow = [];
    const massClose = [];
    const boxWidth = 10,
        boxPadding = 4,
        climbColor = 'green',
        fallColor = 'red',
        svg = d3.select('#boxplot'),
        margin = {
            top: 20,
            right: 0,
            bottom: 20,
            left: 33
        },
        g = svg.append('g').attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');
    for (let i = 0; i < date.length; i++) {
        massDate.push(new Date(Date.parse(date[i])));
        massOpen.push(parseFloat(open[i]));
        massHigh.push(parseFloat(high[i]));
        massLow.push(parseFloat(low[i]));
        massClose.push(parseFloat(close[i]));
    }
    const svgWidth = (boxWidth + boxPadding) * (date.length + 1) + boxPadding + margin.left + margin.right;
    const svgHeight = 300 + margin.top + margin.bottom;
    svg.style('height', 340).style('width', 2100);
    const width = svgWidth * 5 - margin.left - margin.right;
    const height = svgHeight - margin.top - margin.bottom;

    let yMin = d3.min(low);
    let yMax = d3.max(high);
    let xMin = d3.min(massDate);
    let xMax = d3.max(massDate);
    const xScale = d3.scaleTime().domain([xMin, xMax]).range([0, width]);
    const yScale = d3.scaleLinear().domain([yMin, yMax]).range([height, 0]);

    const xAxis = d3.axisBottom(xScale).ticks(d3.timeDay);
    const yAxis = d3.axisRight(yScale).tickSize(width)
        .tickFormat(function (d) {
            return d3.format('.4f')(d);
        })
    g.append('g').attr('id', 'xAxisG')
        .attr('transform', 'translate(0,' + height + ')')
        .call(function (g) {
            g.call(xAxis)
            g.select('.domain').remove()
        })
    g.append('g').attr('id', 'yAxisG')
        .call(function (g) {
            g.call(yAxis)
            g.select('.domain').remove()
            g.selectAll('.tick line').attr('stroke', '#777').attr('stroke-dasharray', '2,2')
            g.selectAll('.tick text').attr('x', -1*margin.left)
        })
    g.select('#yAxisG').append('line')
        .attr('x1', -1).attr('y1', -1).attr('x2', -1).attr('y2', height)
        .style('stroke', '#000')
        .style('stroke-width', 1)

    g.select('#xAxisG').append('line')
        .attr('x1', 0).attr('y1', 0).attr('x2', width).attr('y2', 0)
        .style('stroke', '#000')
        .style('stroke-width', 1)
    let data = new Map();
    for (let i = 0; i < massDate.length - 3; i++) {
        let currentValue = [];
        currentValue.push(massOpen[i]);
        currentValue.push(massHigh[i]);
        currentValue.push(massLow[i]);
        currentValue.push(massClose[i]);
        data.set(massDate[i], currentValue);
    }

    for (let i = 1; i < massDate.length - 3; i++) {
        g.append('rect')
            .attr('x', function () {
                return xScale(massDate[i]) - 1;
            })
            .attr('y', function () {
                return yScale(massHigh[i]);
            })
            .attr('fill', function () {
                return massClose[i] > massOpen[i] ? climbColor : fallColor;
            })
            .attr('width', 2)
            .attr('height', function (){
                return Math.abs(yScale(massHigh[i]) - yScale(massLow[i]));
            })
        g.append('rect')
            .attr('x', function () {
                console.log(massDate[i]);
                return xScale(massDate[i]) - 5;
            })
            .attr('y', function () {
                console.log(yScale(Math.max(massOpen[i], massClose[i])));
                return yScale(Math.max(massOpen[i], massClose[i]));
            })
            .attr('fill', function () {
                return massClose[i] > massOpen[i] ? climbColor : fallColor;
            })
            .attr('width', boxWidth)
            .attr('height', function (){
                return Math.abs(yScale(massClose[i]) - yScale(massOpen[i]));
            })
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const year = document.getElementById('j_idt:year_value');
    const month = document.getElementById('j_idt:month_value');
    year.value = '';
    month.value = '';
}, false);

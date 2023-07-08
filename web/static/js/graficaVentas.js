
const ctx = document.getElementById("myChart").getContext('2d');
var primero = new Date();
var segundo = new Date();
segundo.setDate(segundo.getDate() - 1);
var tercero = new Date();
tercero.setDate(tercero.getDate() - 2);
var cuarto = new Date();
cuarto.setDate(cuarto.getDate() - 3);
var quinto = new Date();
quinto.setDate(quinto.getDate() - 4);
var sexto = new Date();
sexto.setDate(sexto.getDate() - 5);
var septimo = new Date();
septimo.setDate(septimo.getDate() - 6);

const names = [septimo.toDateString(),sexto.toDateString(),quinto.toDateString(), cuarto.toDateString(),tercero.toDateString(),segundo.toDateString(),primero.toDateString()];

const ages = [parseInt(document.getElementById("siete").value),parseInt(document.getElementById("seis").value),parseInt(document.getElementById("cinco").value), parseInt(document.getElementById("cuatro").value), parseInt(document.getElementById("tres").value), parseInt(document.getElementById("dos").value), parseInt(document.getElementById("uno").value)];


const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: names,
        datasets: [{
            label: 'Dia',
            data: ages,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    
});

const ctxTwo = document.getElementById("myChartTwo").getContext('2d');
const price = [parseInt(document.getElementById("sieteTot").value),parseInt(document.getElementById("seisTot").value),parseInt(document.getElementById("cincoTot").value), parseInt(document.getElementById("cuatroTot").value), parseInt(document.getElementById("tresTot").value), parseInt(document.getElementById("dosTot").value), parseInt(document.getElementById("unoTot").value)];

const myChartTwo = new Chart(ctxTwo, {
    type: 'doughnut',
    data: {
        labels: names,
        datasets: [{
            label: 'Dia',
            data: price,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    
});
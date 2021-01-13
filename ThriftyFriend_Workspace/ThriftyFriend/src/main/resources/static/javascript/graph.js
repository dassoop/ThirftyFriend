/**
 * Utilize Chart JS to generate a pricing history chart for listing summaries
 */

//grab data from html
var dates = Array.from(document.getElementsByClassName('columnDate'));
//create empty array and push data from html into array
var dateArr = []
	Array.from(dates).forEach(function(date){
		dateArr.push(date.innerHTML)
	});
	
var prices = Array.from(document.getElementsByClassName('columnPrice'));
var priceArr = [];
	Array.from(prices).forEach(function(price){
		priceArr.push(price.innerHTML)
	});

//create chart using chart.js		
var chart = document.getElementById('chart').getContext('2d');

//Set default x-axis to 0
Chart.scaleService.updateScaleDefaults('linear', {
ticks: {
    min: 0
}
});
var priceChart = new Chart(chart,
{
	type: 'line',
    data: {
        labels: dateArr,
        datasets: [{
            label: 'Average cost',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: priceArr
        }]
    },

	
});

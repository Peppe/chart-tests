/**
 * Created by Peppe on 17.09.2015.
 */

window.com_jensjansson_ChartExtension = function() {
        var element = this.getElement(this.getParentId());
        var rpcProxy = this.getRpcProxy();

        this.enableZoomTracking = function() {
            var chartIdx = element.getAttribute("data-highcharts-chart");
            var chart = Highcharts.charts[chartIdx];
            Highcharts.addEvent(chart.xAxis[0],'afterSetExtremes', function(e){
                console.log("A zoom happened to these coordinates");
                console.log(chart.xAxis[0].min, chart.xAxis[0].max);
                console.log(chart.yAxis[0].min, chart.yAxis[0].max);
                if(e.trigger == "zoom"){
                    rpcProxy.onZoomEvent(chart.xAxis[0].min, chart.xAxis[0].max, chart.yAxis[0].min, chart.yAxis[0].max);
                    console.log("a zoom event");
                } else {
                    console.log("NOT a zoom event");
                }

            });
        }

        this.addBadge = function() {
            var chartIdx = element.getAttribute("data-highcharts-chart");
            var chart = Highcharts.charts[chartIdx];
            var renderer = chart.renderer;
            var height = chart.chartHeight;
            var width = chart.chartWidth;
            var circle = renderer.circle(width-150, height-150, 80).attr({
                fill: '#67c1ef',
                stroke: '30aae9',
                'stroke-width': 4
            });
            circle.add();
            circle.toFront();

            var text = renderer.text('Awesome!', width-195, height-120)
                .attr({
                    rotation: -25
                })
                .css({
                    color: 'white',
                    fontSize: '24px'
                });
            text.add();
            text.toFront();
        };
    }
$(function(){
	$('#tab-1').append("<div id=\"cpumain\" style=\"height:300px\" />");
	initCpuChart();
	initMemChart();
	initDiskChart();
})

function initCpuChart(){
	var myChart;
	var eCharts;
	require.config({
		paths : {
			'echarts' : '/js/plugins/echarts'
		}
	});

	require(
		[ 'echarts', 
		  'echarts/chart/line'
		], DrawEChart // 异步加载的回调函数绘制图表
	);
	// 创建ECharts图表方法
	function DrawEChart(ec) {
		eCharts = ec;
		myChart = eCharts.init(document.getElementById('cpumain'));
		myChart.showLoading({
			text : "图表数据正在努力加载..."
		});
		// 定义图表options
		var options = {
			title:{
				text:"CPU",
				textStyle:{
					fontSize: 18,
				    fontWeight: 'normal',
				    color: '#333'
				}
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ "总调用次数",'异常调用次数' ]
			},
			toolbox : {
				show : true,
				feature : {
				    selfButtons:{//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字    
		                   show:true,//是否显示    
		                   title:'刷新', //鼠标移动上去显示的文字 
		                   icon:'img/refresh.png', //图标    
		                   option:{},    
		                   onclick:function(option1) {//点击事件,这里的option1是chart的option信息    
		                	   getChartData(); 
		                       }    
		                    },
					mark : {
						show : false
					},
					dataView : {
						show : false,
						readOnly : false
					},
					magicType : {
						show : false,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : [ '1', '2', '3', '4', '5', '6', '7' ]
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : '{value} 次'
				}
			} ],
			grid : {
				x:60,
				x2:40
			},
			series : [ {
				name : '总调用次数',
				type : 'line',
				smooth : true,
				data : [ 0, 0, 0, 0, 0, 0, 0 ],// 必须是Integer类型的,String计算平均值会出错
				markPoint : {
					data : [ 
					         {type : 'max',name : '最大值'}, 
					         {type : 'min',name : '最小值'} 
					       ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '异常调用次数',
				type : 'line',
				smooth : true,
				data : [ 0, 0, 0, 0, 0, 0, 0 ],// 必须是Integer类型的,String计算平均值会出错
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				}
			}
			]
		};

		myChart.setOption(options); // 先把可选项注入myChart中
		window.onresize = myChart.resize;
		getChartData();
		setInterval(getChartData,60000);
	}

function getChartData() {
	//获得图表的options对象
	var options = myChart.getOption();
	//通过Ajax获取数据
	$.ajax({
		type : "get",
		url : "/invokeerrorscale",
		data : {},
		dataType : "json", //返回数据形式为json
		success : function(result) {
			if (result) {
				options.legend.data = result.legend;
				options.xAxis[0].data = result.category;
				options.series[0].data = result.series[0].data;
				options.series[1].data = result.series[1].data;
				myChart.hideLoading();
				myChart.setOption(options);
			}
		},
		error : function(errorMsg) {
			toastr.options = {
	                closeButton: true,
	                progressBar: true,
	                showMethod: 'slideDown',
	                timeOut: 4000
	            };
            toastr.error('您最后一次登录：2015-12-01 14:23', '陈龙，欢迎使用 SOA+管理系统 ');
			myChart.hideLoading();
		}
	});
	}
}

function initMemChart(){

}

function initDiskChart(){

}
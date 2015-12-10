$(function(){
	initChart();
	initSlow();
})

function initSlow(){
	$.ajax({
		type : "get",
		url : "/invokeslow",
		data : {},
		dataType : "json", //返回数据形式为json
		success : function(result) {
			var lihtml = [];
			$.each(result,function(i,data){
				lihtml.push("<li class='list-group-item'>");
				lihtml.push("<span class='pull-right'>");
			    lihtml.push(data.timeelapsed);
			    lihtml.push("</span>");
			    lihtml.push("<span class='label "+getLevClass(i)+"'>");
			    lihtml.push(i+1);
			    lihtml.push("</span> ");
			    lihtml.push("<a href='/invokedtlshow?'"+data.id+"> ");
			    lihtml.push(data.method);
			    lihtml.push("</a>");
			    lihtml.push("</li>");
			})
			$('#ul_ph').append(lihtml.join('')+'');
		}
	});
	
}

function getLevClass(ind){
	switch(ind){
	  case 0: return 'label-danger';
	  case 1: return 'label-warning';
	  case 2: return 'label-success';
	  case 3: return 'label-info';
	  case 4: return '';
	}
}

function initChart(){
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
		myChart = eCharts.init(document.getElementById('main'));
		myChart.showLoading({
			text : "图表数据正在努力加载..."
		});
		// 定义图表options
		var options = {
			title:{
				text:"实时调用链统计",
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
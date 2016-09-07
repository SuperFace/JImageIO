var Main = {
		width: 0,
		height:0,
		colors: null,
		parseColors: function(){
			var that = this;
			var imgBox = document.createElement("div");
			imgBox.style.cssText = "position:absolute;padding:0;margin:0;border:1px solid #ccc;top:10px;left:10px;width:"+ that.width +"px;height:" + that.height + "px;";
			document.body.appendChild(imgBox);
			var len = that.colors.length;
			var i=0;
			setTimeout(function(){
				if(i < len){
					var color = that.colors[i];
					var left = color[0], top = color[1];
					color = color[2];
					var point = document.createElement("div");
					point.style.cssText = "position:absolute;padding:0;margin:0;top:"+top+"px;left:"+left+"px;width:1px;height:1px;background:"+color+";";
					imgBox.appendChild(point);
					setTimeout(arguments.callee, 0);
				}
				i++;
			}, 0);
		}
};

$(function(){
	$.ajax({
		url: "/servlet3demo",
		data: {},
		success: function(json){
			if(json.status == "ok"){
				Main.width = json.width;
				Main.height = json.height;
				Main.colors = json.colors;
				Main.parseColors();
			}
		}
	});
});
$(function(){
	$("#btn1").click(function(){
		$.ajax({
			url: 'temp.do',
			data: {location: $("#location").val()},
			success: function(result){
				console.log(result);
				const data = result.response.body.items.item;
				
				let after3Data = "<span class='badge text-bg-primary'>최저기온 " + data[0].taMin3 + "&#8451;</span>"
		        			 + "<span class='badge text-bg-danger'>최고기온 " + data[0].taMax3 + "&#8451;</span>"
		        			 
				$("#after3").html(after3Data);
		        			 
				let after7Data = "<span class='badge text-bg-primary'>최저기온 " + data[0].taMin7 + "&#8451;</span>"
    			 + "<span class='badge text-bg-danger'>최고기온 " + data[0].taMax7 + "&#8451;</span>"
    			 
				$("#after7").html(after7Data);
    			 
				let after10Data = "<span class='badge text-bg-primary'>최저기온 " + data[0].taMin10 + "&#8451;</span>"
    			 + "<span class='badge text-bg-danger'>최고기온 " + data[0].taMax10 + "&#8451;</span>"
    			 
				$("#after10").html(after10Data);
    			 
			},
			error: function(err){
				console.log(err);
			}
		});
	});

	function updateTime() {
        const date = new Date();
        
        const formattedTime = date.toLocaleString('ko-KR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            second: 'numeric',
            hour12: true
        });
        $("#now").text(formattedTime);
    }
	
	// 처음 페이지 로드 시 현재 시간 표시
    updateTime();

    // 1초마다 현재 시간 업데이트
    setInterval(updateTime, 1000);
});

$(function(){
	// btn1 아이디 속성을 가진 요소의 클릭 이벤트
	// jquery는 $()안에 선택자를 작성해서 접근 가능
	// onclick은 자바스크립트 방식이고 jquery는 click
	
	// btn1 아이디 속성을 가진 요소의 클릭 이벤트
	$("#btn1").click(function(){
		// ajax 통신 : 요청 주소 - air.do, 요청 데이터 - {location: 선택된 지역정보}
		// json 방식으로 데이터를 응답받는 방법
		/*
		$.ajax({
			url: 'air.do', 
			data: {location: $("#location").val() },
			success: function(result){
				// 응답 성공일 경우 실행 코드
				console.log(result);
				
				const data = result.response.body.items;
				
				let bodyData = "";
				for(let i=0; i<data.length; i++){
					bodyData += "<tr>"
								+ "<td>" + data[i].stationName + "</td>"
								+ "<td>" + data[i].dataTime + "</td>"
								+ "<td>" + data[i].khaiValue + "</td>"
								+ "<td>" + data[i].pm10Value + "</td>"
								+ "<td>" + data[i].coValue + "</td>"
								+ "<td>" + data[i].no2Value + "</td>"
								+ "<td>" + data[i].so2Value + "</td>"
								+ "<td>" + data[i].o3Value + "</td>"
							 + "</tr>"
				}
				
				$("#ap-table tbody").html(bodyData);
			}, 
			error: function(err){
				console.log(err);
			}
		});
		*/
		getAirPollution("air.do", 1, function(result){
			console.log(result);
			
			// 응답데이터를 화면에 표시하는 부분을 함수로 분리!
			appendJsonData(result);
		});
	});

	// btn2 아이디 속성을 가진 요소의 클릭 이벤트
	$('#btn2').click(function(){
		// xml 형식으로 응답 받기
		/*
		$.ajax({
			url: 'air2.do',
			data: {
				location: $("#location").val()
			},
			success: (result) => {
				console.log(result);

				const itemArr = $(result).find("item"); // [<item/>, <item/>, <item/>, ...]

				let tbodyData = "";
				itemArr.each((index, item) => {
					console.log($(item));
					
					tbodyData += "<tr>"
									+"<td>" + $(item).find("stationName").text() + "</td>"
									+"<td>" + $(item).find("dataTime").text() + "</td>"
									+"<td>" + $(item).find("khaiValue").text() + "</td>"
									+"<td>" + $(item).find("pm10Value").text() + "</td>"
									+"<td>" + $(item).find("coValue").text() + "</td>"
									+"<td>" + $(item).find("no2Value").text() + "</td>"
									+"<td>" + $(item).find("so2Value").text() + "</td>"
									+"<td>" + $(item).find("o3Value").text() + "</td>"
								+"</tr>"
				})
				$("#ap-table tbody").html(tbodyData);

			},
			error: (err) => {
				console.log(err);
			}
		});
		*/
		getAirPollution("air2.do", 1, (result)=>{
			console.log(result);
			appendXMLData(result);
		});
	});
});
              

const getAirPollution = (url, pageNo, callback) => {
	$.ajax({
		url: url,
		data: {
			location: $("#location").val(),
			pageNo: pageNo
		},
		success: (result) => {
			// 매개변수로 전달된 콜백함수(callback)를 응답데이터(result)를 담아 호출
			callback(result);
		},
		error: (err) => {
			console.log(err);
			
		}
	})
}



const getMoreData = (nextNo) => {
	// [더보기] 버튼이 클릭되었을 때 데이터를 추가로 요청
	getAirPollution("air.do", nextNo, (result)=>{
		appendJsonData(result)
	});
}


const appendJsonData = (result) => {
	const data = result.response.body.items;
	
	let bodyData = $("#ap-table tbody").html();	// 처음에는 tbody 부분이 비어있을 것이고, 데이터를 조회한 경우는 데이터들이 (tr...) 있을 것임!
	
	for(let i=0; i<data.length; i++){
		bodyData += "<tr>"
					+ "<td>" + data[i].stationName + "</td>"
					+ "<td>" + data[i].dataTime + "</td>"
					+ "<td>" + data[i].khaiValue + "</td>"
					+ "<td>" + data[i].pm10Value + "</td>"
					+ "<td>" + data[i].coValue + "</td>"
					+ "<td>" + data[i].no2Value + "</td>"
					+ "<td>" + data[i].so2Value + "</td>"
					+ "<td>" + data[i].o3Value + "</td>"
				+ "</tr>"
	}
	
	$("#ap-table tbody").html(bodyData);

	$('#ap-table + button').remove();
	
	if( $("#ap-table tbody tr").length < result.response.body.totalCount ){
		$("#ap-table").after("<button class='btn btn-dark w-100' onclick='getMoreData("+(result.response.body.pageNo+1)+")'>더 보기</button>");
	}
}




const getMoreDataXML = (nextNo) => {
	// [더보기] 버튼이 클릭되었을 때 데이터를 추가로 요청
	getAirPollution("air2.do", nextNo, (result)=>{
		appendXMLData(result);
	});
}

const appendXMLData = (result) => {
	const itemArr = $(result).find("item"); // [<item/>, <item/>, <item/>, ...]

	let tbodyData = $("#ap-table tbody").html();
	itemArr.each((index, item) => {
		console.log($(item));
		
		tbodyData += "<tr>"
						+"<td>" + $(item).find("stationName").text() + "</td>"
						+"<td>" + $(item).find("dataTime").text() + "</td>"
						+"<td>" + $(item).find("khaiValue").text() + "</td>"
						+"<td>" + $(item).find("pm10Value").text() + "</td>"
						+"<td>" + $(item).find("coValue").text() + "</td>"
						+"<td>" + $(item).find("no2Value").text() + "</td>"
						+"<td>" + $(item).find("so2Value").text() + "</td>"
						+"<td>" + $(item).find("o3Value").text() + "</td>"
					+"</tr>"
	});

	$("#ap-table tbody").html(tbodyData);

	$('#ap-table + button').remove();

	if( $("#ap-table tbody tr").length < $(result).find("totalCount").text() ){
		$("#ap-table").after("<button class='btn btn-dark w-100' onclick='getMoreDataXML("+(parseInt($(result).find("pageNo").text())+1)+")'>더 보기</button>");
	}
}
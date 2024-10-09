<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OpenAPI - 대기오염지수</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	
</head>
<body>
    <div class="p-5" align="center">
        <h2 class="p-2 bg-primary bg-opacity-25">실시간 대기오염 정보</h2>
        <br><br>
        <div class="input-group">
            <span class="input-group-text bg-dark text-white">지역</span>
            <select id="location" class="form-select w-25">
                <option>서울</option>
                <option>인천</option>
                <option>경기</option>
                <option>강원</option>
                <option>부산</option>
            </select>
            <button id="btn1" class="btn btn-dark">대기오염 정보 보기</button>
        </div>
        <br><br>
        <table id="ap-table" class="table table-hover table-striped text-center">
            <thead>
                <tr class="table-dark">
                    <th>측정소</th>
                    <th>측정일시</th>
                    <th>통합대기환경수치</th>
                    <th>미세먼지농도</th>
                    <th>일산화탄소농도</th>
                    <th>이산화질소농도</th>
                    <th>아황산가스농도</th>
                    <th>오존농도</th>
                </tr>
            </thead>
            <tbody>
            
            </tbody>
        </table>
        <script>
        	// 비동기 통신 (ajax)
        	
        	// onload 시점에 jqeury 표현법
        	$(function(){
        		// btn1 아이디 속성을 가진 요소의 클릭 이벤트
        		// jquery는 $()안에 선택자를 작성해서 접근 가능
        		// onclick은 자바스크립트 방식이고 jquery는 click
        		$("#btn1").click(function(){
        			// ajax 통신 : 요청 주소 - air.do, 요청 데이터 - {location: 선택된 지역정보}
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
        		});
        	});
        </script>
    </div>
</body>
</html>
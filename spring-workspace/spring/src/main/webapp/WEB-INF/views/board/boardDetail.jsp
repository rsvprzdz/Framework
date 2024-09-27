<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
    <style>
        .outer {
            background-color: #e7e7e7;
            width: 80%;
            margin: auto;
        }
        .inner-area {
            border: 1px solid #000025;
            width: 80%;
            margin: auto;
            padding: 5% 15%;
            background: #e7ecf7;
        }
        
        table {width:100%;}
        table * {margin:5px;}
    </style>    
</head>
<body>
    <%-- header --%>
    <%-- <jsp:include page="../common/header.jsp" /> --%>
   <%@ include file = "../common/header.jsp" %>
    <div class="outer">
        <br><br>
        <div class="inner-area">
            <h2>게시글 상세보기</h2>
            <br>
            <a href="list" class="btn btn-secondary" style="float:right;">목록보기</a>
            <br><br>

            <table align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">
                    ${b.boardTitle}
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                    ${b.boardWriter}
                    </td>
                    <th>작성일</th>
                    <td>
                     ${b.createDate}
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                        <c:choose>
                           <c:when test="${ not empty b.originName }">
                           
                              <a href="<%= request.getContextPath() %>/"${b.changeName}" download="${ b.originName }" >"${ b.originName }"</a>                              
                              
                           </c:when>
                           
                           <c:otherwise>
                           
                              첨부파일없음

                           </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <p style="height:150px;">
                            ${b.boardContent}
                        </p>
                    </td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 작성자와 로그인한 계정이 동일한 경우만 표시 -->
                <c:if test="${ loginUser.userId eq b.boardWriter }">
                   <a class="btn btn-primary" onclick="postSubmit('update');">수정</a>
                   <!-- updateForm?bno=${b.boardNo} get요청방식 -->
                   <a class="btn btn-danger" onclick="postSubmit('delete');">삭제</a>
               </c:if>
            </div>
            <br><br>
            
            <form action="" method="post" id="postForm">
               <input type="hidden" name="bno" value="${b.boardNo}"/>
            </form>
            
            <script>
               function postSubmit(type){
                  const postForm =  document.getElementById("postForm");
                  //document.querySelector("#postForm")
                  
                  if(type == 'update'){
                     //게시글 수정 페이지로 요청
                     postForm.action = 'updateForm';
                  } else if (type == 'delete'){
                     //게시글 삭제 요청
                     postForm.action = 'delete';
                  }
                  
                  postForm.submit();
               }
            </script>

            <table id="replyArea" class="table" align="center">
                <thead>
                   <%-- 로그인한 사용자만 댓글을 작성할 수 있도록
                         => 만약, 로그인을 하지 않았다면 입력창 부분에 '로그인 후 이용가능합니다.'
                          메시지를 표시하고 입력하지 못하도록 [등록]버튼을 비활성화
                    --%>
                   <c:choose>
                      <c:when test="${ not empty loginUser}">
                      <%-- 로그인 했을 경우 --%>
                      <tr>
                         <th colspan="2">
                            <textarea name="" id="content" cols="55" rows="2" class="form-control" style="resize: none;"></textarea>
                           </th>
                             <th style="vertical-align:middle;">
                            <button class="btn btn-secondary" onclick="addReply();">등록</button>
                            
                           </th>
                       </tr>
                      </c:when>
                      <c:otherwise>
                      <%-- 로그인 하지 않았을 경우 --%>
                      <tr>
                         <th colspan="2">
                            <textarea name="" id="content" cols="55" rows="2" class="form-control" style="resize: none;" placeholder="로그인 후 이용 가능합니다." readonly></textarea>
                           </th>
                             <th style="vertical-align:middle;">
                            <button class="btn btn-secondary" disabled>등록</button>
                           </th>
                       </tr>
                      </c:otherwise>
                   </c:choose>
                    
                        
                    <tr>
                        <td colspan="3">댓글 (<span id="rcount">0</span>)</td>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>     
            <br><br>
        </div>


    </div>
    
    <script>
       //onload
       $(function(){
          //해당 게시글의 댓글 목록 조회(ajax)
          selectReplyList();
       });
       
       function selectReplyList(){
          $.ajax({
             url: "rlist",
             data: { bno: ${ b.boardNo } },
             success: function(result){
                console.log(result);
                
                //댓글 목록이 있을 경우 화면에 표시
                // => 댓글 목록 없을 경우 : 빈 배열
                // => 댓글 목록 있는 경우 : 배열에 데이터 담겨져있음
                if (result != null && result.length > 0) {
                   let replyValue = "";
                   
                   for(let r of result){
                      replyValue += "<tr>"
                                  + "<th>" + r.replyWriter + "</th>"
                                  + "<td>" + r.replyContent + "</td>"
                                  + "<td>" + r.createDate + "</td>"
                                 +"</tr>";
                      
             /*
             <tr>
                        <th>user02</th>
                        <td>댓글-----내용</td>
                        <td>2024-04-15</td>
                </tr>
                         
             */
                   }
                   
                   $("#replyArea tbody").html(replyValue);
                   $("#rcount").text(result.length);
                }
                
             },
             error: function(err) {
                console.log("댓글 조회 실패!");
                console.log(err);
             }
          });
       }
       
       function addReply(){
          <%-- 등록버튼 클릭 시 alert실행 *입력된 내용을 출력 --%>
          //alert( $("#replyArea #content").val() );
          
          //입력된 내용이 있을 경우 추가 요청하도록 --trim()넣어서 공백은 제거
          if ( $("#replyArea #content").val().trim().length>0 ){
          //댓글 추가 요청 (ajax) :
          // =>/spring/board/rinsert?replyContent=입력내용&refBno=게시글번호&replyWriter=작성자
          $.ajax({
             url : "rinsert",   //요청주소
             data : {replyContent: $("#replyArea #content").val(), refBno: '${b.boardNo}', replyWriter: '${loginUser.userId}' }, //전달데이터
             success: function(result){   //요청성공 시 (통신성공)
                console.log(result);
                //댓글 추가 성공 시, 입력창 부분을 초기화 댓글 목록 다시 조회
                if (result == "success") {
                   $("#replyArea #content").val('');
                   selectReplyList();                   
                } else {
                   //댓글 추가 실패 시, '댓글 추가에 실패했습니다.'메시지를 출력(alert)
                   alert("댓글 추가에 실패했습니다.");   
                }
             },
             error: function(err) {   //요청실패 시 (통신실패)
               console.log("댓글 추가 요청 실패!");
               console.log(err);
             }
          });
       } else {
          alert("내용 입력 후 추가 가능합니다.");
       }
    }
    </script>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />    
</body>
</html>
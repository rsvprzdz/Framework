/**
 * 
 */

// [인증 요청] 버튼 클릭 이벤트 => 알림창 표시 : "인증 요청"

// [확인] 버튼 클릭 이벤트 =? 알림창 표시: "확인"


window.onload = () => {
    document.getElementById("btn-auth").onclick = () =>{

        // (POST) /mail
        //        {email: {인증코드를 받을 이메일 주소}}
        
        // 스피너 활성화 => 요청이 진행중임을 표시하기 위해
        $("#spinner").removeClass("d-none");
        // [인증요청] 버튼 비활성화 
        $("#btn-auth").addClass("disabled");

        // 비동기 통신 => ajax
        $.ajax({
            url: 'mail',
            type: 'post',
            data: {
                email: $("#email").val()

            },
            success: function(result){

                // 요청 완료 후 스피너: 비활성화, 인증요청 버튼: 활성화
                $("#spinner").addClass("d-none");
                $("#btn-auth").removeClass("disabled");
                alert(result);
            },
            error: (err) => {
                console.log(err);
                
            }

        });


    }

    // [확인] 버튼 클릭 이벤트 
    document.getElementById("btn-check").onclick = () =>{

        // alert("확인");

        // 입력된 코드 값을 /check 요청
        $.ajax({
            url: '/check',
            type: 'post',
            data: {
                code: $("#authCode").val(),
                email: $("#email").val()
            },
            success: (result) => {
                alert(result);
            },
            error: (err) =>{
                console.log(err);
            }
        });

    }
}
// onload 시점에 이벤트 핸들러 추가, onload는 window 객체가 가지고 있는데 window는 생략 가능

onload = function() {
	
    // btn-google 이라는 아이디 속성을 가진 요소의 클릭 이벤트

    const CLIENT_ID = "445734127873-mhqcvdir04js2f4hdh8end47ud0cf46j.apps.googleusercontent.com";
    const REDIRECT_URI = "http://localhost:7777/login/oauth/google";

    // document.getElementById("#btn-google").onclick
    document.querySelector("#btn-google").onclick = () => {
        // alert("구글로그인!@");
        const url = "https://accounts.google.com/o/oauth2/v2/auth"
                    + "?client_id=" + CLIENT_ID
                    + "&redirect_uri=" + REDIRECT_URI
                    + "&response_type=code"
                    + "&scope=email profile";

        location.href = url;
    }
}
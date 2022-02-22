$login = {
    login: function () {
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;

        if (!$login.validate(email, password)) {
            return;
        }

        var url = '/api/login';
        var param = {
            email: email,
            password: password
        }

        $ajax.post(url, param, $login.callback, $login.errCallback);
    },

    validate: function (email, password) {
        document.getElementById('emailErr').innerText = '';
        document.getElementById('pwdErr').innerText = '';

        if (email === '') {
            document.getElementById('emailErr').innerText = '이메일을 입력해주세요';
            return false;
        } else if (email.indexOf('@') < 0) {
            document.getElementById('emailErr').innerText = '이메일을 형식으로 입력해주세요';
            return false;
        }

        if (password === '') {
            document.getElementById('pwdErr').innerText = '비밀번호를 입력해주세요';
            return false;
        }
        return true;
    },

    callback: function (response) {

        console.log(response);
        var rtnCd=JSON.parse(response).rtnCd;
        if(rtnCd==0){
            window.location.href = "/home";
        }
        else{
            var msg=JSON.parse(response).rtnMsg;
            alert(msg);

        }
        console.log(rtnCd);
    },

    errCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");

    }


}
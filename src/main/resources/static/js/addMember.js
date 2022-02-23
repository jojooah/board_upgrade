$addMember = {

    addMember: function () {
        var email = document.getElementById('email').value;
        var name = document.getElementById('name').value;
        var password = document.getElementById('password').value;
        var ch_password = document.getElementById('ch_password').value;

        if (!$addMember.validate(email, name, password, ch_password)) {
            return;
        }

        url = 'api/addMember';
        param = {
            email: email,
            name: name,
            password: password,
            ch_password: ch_password
        }
        $ajax.post(url, param, $addMember.callback, $addMember.errCallback);

    },
    validate: function (email, name, password, ch_password) {
        document.getElementById('emailErr').innerText = '';
        document.getElementById('nameErr').innerText = '';
        document.getElementById('pwdErr').innerText = '';
        document.getElementById('ch_pwdErr').innerText = '';
        if (email === '') {
            document.getElementById('emailErr').innerText = '이메일을 입력해 주세요';
            return false;
        } else if (email.indexOf('@') < 0) {
            document.getElementById('emailErr').innerText = '이메일 형식으로 입력해주세요';
            return false;
        } else if (name === '') {
            document.getElementById('nameErr').innerText = '이름을 입력해 주세요';
            return false;
        } else if (password === '') {
            document.getElementById('pwdErr').innerText = '비밀번호를 입력해 주세요';
            return false;
        } else if (ch_password === '') {
            document.getElementById('ch_pwdErr').innerText = '비밀번호를 한번 더 입력해 주세요';
            return false;
        } else if (ch_password != password) {
            document.getElementById('ch_pwdErr').innerText = '비밀번호가 서로 달라요';
            return false;
        }
        return true;

    },

    callback: function (response) {


        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {
            var message = '회원가입이 완료되었습니다!';
            location.href = "/login?message=" + message;
            alert(message);

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);

        }

    },

    errCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");

    }

}
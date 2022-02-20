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
    },

    errCallback: function (response) {
        console.log(response);
    }


}
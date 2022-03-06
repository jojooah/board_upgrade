$addBoard = {
    category:document.getElementById('category').value,

    addBoard: function () {

        var title = document.getElementById('title').value;
        var content = document.getElementById('content').value;

        if (!$addBoard.validate(title, content)) {
            return;
        }

        url = '/api/addBoard';
        param = {
            title: title,
            content: content,
            category:$addBoard.category
        }
        $ajax.post(url, param, $addBoard.callback, $addBoard.errCallback);

    },
    validate: function (title, content) {
        document.getElementById('title').innerText = '';
        document.getElementById('content').innerText = '';

        if (title === '') {
            document.getElementById('title').innerText = '제목을 입력해 주세요';
            return false;
        } else if (content === '') {
            document.getElementById('content').innerText = '내용을 입력해 주세요';
            return false;
        }
        return true;

    },

    callback: function (response) {


        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {
            location.href = "/boardList?category=" +$addBoard.category;

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
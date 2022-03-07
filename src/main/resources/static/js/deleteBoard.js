$deleteBoard = {
    category: document.getElementById('category').value,

    deleteBoard: function () {
        var id = document.getElementById('id').value;
        url = '/api/board/deleteBoard';
        param = {
            id: id
        }
        $ajax.get(url, param, $deleteBoard.callback, $deleteBoard.errCallback);

    },


    callback: function (response) {

        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {
            location.href = "/boardList?category=" + $deleteBoard.category;
            alert("글이 삭제되었습니다.");

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);

        }

    },

    errCallback: function (response) {
        console.log(response);//에러화면 띄우기
        location.href = "/boardList?category=" + $addBoard.category;
        alert("잠시 후 다시 시도해 주세요");

    }

}
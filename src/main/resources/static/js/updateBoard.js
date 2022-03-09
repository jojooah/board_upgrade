$updateBoard = {
    category: document.getElementById('category').value,
    id: document.getElementById('id').value,

    updateBoard: function () {
        var title = document.getElementById('title').value;
        var content = document.getElementById('content').value;
        var id = $updateBoard.id;
        var category = $updateBoard.category;
        var url = '/api/board/update';
        console.log(title,id,content,category);
        var param = {
            id: id,
            title: title,
            content: content,
            category: category
        }
        $ajax.post(url, param, $updateBoard.callback, $updateBoard.errCallback);

    },

    callback: function (response) {


        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {
            location.href = "/board/content?id=" + $updateBoard.id + "&category=" + $updateBoard.category;
            alert("글이 수정되었습니다.");

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




$boardContent = {

    getContent: function () {

        var id = document.getElementById('id').value;
        var url = '/api/board/boardContent';
        console.log(id);
        var param = {
            id: id
        }
        $ajax.post(url, param, $boardContent.getContentCallBack, $boardContent.getContentErrCallBack);

    },

    getContentCallBack: function (response) {

        var result = JSON.parse(response);
        console.log(result);

        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd != 0) {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);

        } else {
            var boardContent = JSON.parse(response).rtnObj;

            document.getElementById("title").innerText='제목: '+boardContent.title;
            document.getElementById("content").innerText='내용: '+boardContent.content;
            document.getElementById("like").innerText='좋아요: '+boardContent.like;
            document.getElementById("name").innerText='유저이름: '+boardContent.name;

        }

    },

    getContentErrCallBack: function () {
        alert("목록을 불러오지 못 했습니다.");
    }
}


document.addEventListener("DOMContentLoaded", function () {
        $boardContent.getContent();
    }
);




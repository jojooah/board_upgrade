$likeBoard = {
    boardId:document.getElementById('id').value,
    memberId:document.getElementById('sessionId').value,

    likeBoard: function () {
        var liked = document.getElementById('liked').value;

        if (!$likeBoard.validate(liked)) {
            return;
        }

        var url = '/api/board/like';
        var param = {
            boardId:$likeBoard.boardId,
            memberId:$likeBoard.memberId
        }

        $ajax.post(url, param, $likeBoard.callback, $likeBoard.errCallback);
    },


    validate(liked){
        if(liked==2){
            alert("이미 좋아요 한 게시물 입니다.");
            return false;
        }else if(liked==3){
            alert("다시 로그인 해 주세요.");
            return false;
        }
        return true;
    },
    callback: function (response) {


        console.log(response);
        var rtnCd=JSON.parse(response).rtnCd;
        if(rtnCd==0){

            var like=JSON.parse(response).rtnObj;
            console.log(like);
            document.getElementById('like').innerText='좋아요: '+like;
            var btn=document.getElementById("like_btn");
            btn.disabled='true';

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
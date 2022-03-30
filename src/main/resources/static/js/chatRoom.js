$chatRoom = {
    createChatRoom: function () {
        var chatRoomTitle = window.prompt("채팅방 이름을 입력하세요", "알 수 없는 채팅방");
        url = '/api/chatRoom';
        param = {
            chatRoomTitle: chatRoomTitle
        }
        $ajax.post(url, param, $chatRoom.callback, $chatRoom.errCallback);

    },
    validate: function (title) {
        document.getElementById('title').innerText = '';


        if (title === '') {
            document.getElementById('title').innerText = '제목을 입력해 주세요';
            return false;
        }
        return true;

    },

    callback: function (response) {
        var rtnCd = JSON.parse(response).rtnCd;
        if (rtnCd == 0) {
            alert("채팅방이 잘 만들어졌습니다");
            return;
        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },

    errCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");
        return;
    }

}
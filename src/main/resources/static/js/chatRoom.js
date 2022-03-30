
$chatRoom = {

    createChatRoom: function () {
        var chatRoomTitle = window.prompt("채팅방 이름을 입력하세요", "알 수 없는 채팅방");
        url = '/api/chatRoom';
        param = {
            chatRoomTitle: chatRoomTitle
        }
        $ajax.post(url, param, $chatRoom.createChatRoomCallback, $chatRoom.createChatRoomErrCallback);

    },

    getChatRooms: function () {
        url = '/api/chatRooms';
        param = {}

        $ajax.get(url, param, $chatRoom.getChatRoomsCallback, $chatRoom.getChatRoomsErrCallback);

    },

    getChatRoomsCallback: function (response) {
        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {

            var chatRooms = JSON.parse(response).rtnObj;
            var content = '';

            chatRooms.forEach(function (chatRoom) {
                var id = chatRoom['id'];
                var title = chatRoom['title'];
                var room = '<a href="javascript:;"onclick="$chatRoom.chatContent(' + id + ')"> '+ title +'</a></br>';

                content += room;
                console.log(room);
            });

            chatUrl = '/Chat';
            var win = window.open(chatUrl, '_blank');

            win.onload = function () {
                win.document.getElementById('chatId').innerHTML = content;
            };

            return;

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },
    chatContent: function (chatId) {

        url = '/api/chatRooms/'+chatId;
        param = {
            chatId:chatId
        }
        $ajax.get(url, param, $chatRoom.chatContentCallback, $chatRoom.chatContentErrCallback);

    },
    chatContentCallback:function (response){

        alert('성공');

    },
    chatContentErrCallback:function (){
        alert('실패');
    },

    getChatRoomsErrCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");
        return;
    },

    createChatRoomCallback: function (response) {
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

    createChatRoomErrCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");
        return;
    }

}

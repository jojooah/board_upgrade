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

    getChatMessages: function (chatRoomId) {
        console.log('time');
        url = '/api/chatRooms/' + chatRoomId + '/messages';

        param = {}
        $ajax.get(url, param, $chatRoom.getChatMessagesCallback, $chatRoom.getChatMessagesErrCallback);

    },

    enterChatRoom: function (chatRoomId) {
        var memberId = document.getElementById('memberId').value;

        url = '/api/chatRooms/' + chatRoomId + '/member';
        param = {
            memberId: memberId
        }
        $ajax.post(url, param, $chatRoom.enterChatRoomCallback, $chatRoom.enterChatRoomErrCallback);

    },

    addMessage: function () {
        var chatRoomId = document.getElementById('chatRoomId').value;
        var message = document.getElementById('message').value;
        var memberId = document.getElementById('memberId').value;

        document.getElementById('message').value = '';
        url = '/api/chatRooms/' + chatRoomId + '/message';
        param = {
            chatRoomId: chatRoomId, message: message, memberId: memberId
        }
        $ajax.post(url, param, $chatRoom.addMessageCallback, $chatRoom.addMessageErrCallback);

    },

    enterChatRoomCallback: function (response) {
        var rtnCd = JSON.parse(response).rtnCd;
        var chatRoomId = JSON.parse(response).rtnObj.chatRoomId;

        if (rtnCd == 0) {
            chatUrl = '/Chat/' + chatRoomId;
            var win = window.open(chatUrl, '_blank');

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },

    getChatRoomsCallback: function (response) {
        var rtnCd = JSON.parse(response).rtnCd;

        if (rtnCd == 0) {

            var chatRooms = JSON.parse(response).rtnObj;
            var content = '';

            chatRooms.forEach(function (chatRoom) {
                var chatRoomId = chatRoom['id'];
                var title = chatRoom['title'];
                var room = '<a href="javascript:;"onclick="$chatRoom.enterChatRoom(' + chatRoomId + ')"> ' + title + '</a></br>';

                content += room;

            });
            document.getElementById('chatRoomId').innerHTML = '</br>' + content;

            return;

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },

    getChatRoomsErrCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert("잠시 후 다시 시도해 주세요");
        return;
    },

    enterChatRoomErrCallback: function (response) {
        console.log(response);//에러화면 띄우기
        alert('채팅방 입장 실패. 로그인 하세용');
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
    },

     addMessageCallback: function (response) {
        var rtnCd = JSON.parse(response).rtnCd;
        if (rtnCd == 0) {
            return;
        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },

    addMessageErrCallback: function (response) {
        alert('서버에러입니당');
    },

    getChatMessagesCallback: function (response) {
        var result = JSON.parse(response);

        if (result['rtnCd'] == 0) {
            var rtnObj = result['rtnObj'];

            var messages = rtnObj['messages'];
            var content = ''
            messages.forEach(function (message) {

                var chat_message = message['message'];
                content += chat_message + '</br>';

            });

            document.getElementById('content').innerHTML = '</br>' + content;
            return;

        } else {
            var msg = JSON.parse(response).rtnMsg;
            alert(msg);
            return;
        }
    },

    getChatMessagesErrCallback: function (response) {
        alert("메시지못가져옴");
    },

}

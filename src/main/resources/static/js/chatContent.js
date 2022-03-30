$chatContent={
    chatContent: function (chatId) {

        url = '/api/chatRooms/'+chatId;
        param = {
            chatId:chatId
        }
        $ajax.get(url, param, $chatContent.callback, $chatContent.ErrCallback);

    },
    callback:function (response){
        alert('성공');

    },
    ErrCallback:function (){

    }
}
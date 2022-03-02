$boardList = {
    category: document.getElementById('category').value,
    getPage: function (pageNum) {
        var url = 'api/board/getList';
        var category = $boardList.category;
        var param = {
            pageNum: pageNum,
            count: 10,
            category: category
        }
        $ajax.get(url, param, $boardList.getPageCallBack, $boardList.getPageErrCallBack);

    },

    getPageCallBack: function (response) {
        document.getElementById('boardTable').getElementsByTagName('tbody')[0].innerHTML = '';
        var result = JSON.parse(response);
        console.log(result);

        if (result['rtnCd'] != 0) {
            if (result['rtnMsg'] != '') {
                alert(result['rtnMsg']);
            } else {
                alert("목록을 불러오지 못 했습니다.");
            }
            return;
        }

        var rtnObj = result['rtnObj'];
        var totalPages = rtnObj['totalPages'];
        var total = rtnObj['total'];
        document.getElementById('boardCount').innerHTML = total;

        var contents = rtnObj['contents'];

        contents.forEach(function (content) {
            var id = content['id'];
            var like = content['like'];
            var title = content['title'];
            var tr = '<tr>' +
                '<td>' + id + '</td>' +
                '<td>' + like + '</td>' +
                '<td>' + title + '</td>' +
                '<td></td>' +
                '</tr>'
            document.getElementById('boardTable').getElementsByTagName('tbody')[0].innerHTML += tr;
            }
        );


    },

    getPageErrCallBack: function () {
        alert("목록을 불러오지 못 했습니다.");
    },

    write: function (){
        console.log("write");
    }

}


document.addEventListener("DOMContentLoaded", function () {
        $boardList.getPage(0);
    }
);




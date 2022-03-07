$boardList = {
    category: document.getElementById('category').value,
    getPage: function (pageNum) {
        $boardList.pageNum = pageNum;
        var url = 'api/board/getList';
        var category = $boardList.category;
        var param = {
            pageNum: $boardList.pageNum,
            count: 3,
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
                    '<td>' +
                    '<a style="text-decoration: none;color: inherit;" href="/board/content?id=' + id + '&category=' + $boardList.category + '">' + title + '</a>' +
                    '</td>' +
                    '</tr>'
                document.getElementById('boardTable').getElementsByTagName('tbody')[0].innerHTML += tr;
            }
        );

        document.getElementById('pagination')

        var pageNum = $boardList.pageNum + 1;
        var start = pageNum - 2;
        var end = pageNum + 2;
        if (start < 1) {
            end += (1 - start);
            start = 1;
        }

        if (end > totalPages) {
            if (start != 1) {
                start -= (end - totalPages);
            }
            end = totalPages
        }


        var pagination;

        if (start - 1 == 0) {
            pagination = '<li class="page-item disabled">\n' +
                '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(0)">Previous</a>\n' +
                '                </li>';
        } else {
            pagination = '<li class="page-item">\n' +
                '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(0)">Previous</a>\n' +
                '                </li>';
        }


        for (var i = start; i <= end; i++) {
            if (i - 1 == $boardList.pageNum) {
                pagination += '<li class="page-item disabled">\n' +
                    '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(' + (i - 1).toString() + ')">' + i + '</a>\n' +
                    '                </li>'
            } else {
                pagination += '<li class="page-item">\n' +
                    '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(' + (i - 1).toString() + ')">' + i + '</a>\n' +
                    '                </li>'
            }
        }

        if (end == totalPages) {
            pagination += '<li class="page-item disabled">\n' +
                '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(' + (totalPages - 1).toString() + ')">Next</a>\n' +
                '                </li>'
        } else {
            pagination += '<li class="page-item">\n' +
                '                    <a href="javascript:;" class="page-link" onclick="$boardList.getPage(' + (totalPages - 1).toString() + ')">Next</a>\n' +
                '                </li>'
        }
        document.getElementById('pagination').innerHTML = pagination;

    },

    getPageErrCallBack: function () {
        alert("목록을 불러오지 못 했습니다.");
    },

    write: function () {

        location.href = "/board/form?category=" + $boardList.category;
        console.log("write11111111");

    }

}


document.addEventListener("DOMContentLoaded", function () {
        $boardList.getPage(0);
    }
);




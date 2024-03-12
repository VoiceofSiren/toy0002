let jsButton = document.getElementById("jsButton");
let id = document.getElementById("bId").value();

jsButton.addEventListener('click', deleteBoard);

function deleteBoard(id) {
    $.ajax({
        // @DELETE, /api/boards/{id}
        url: '/api/boards/' + id,
        type: 'DELETE',
        success: function(result) {
            console.log('result', result);
            alert('삭제되었습니다.');
            window.location.href = '/board/list';
    }});
}

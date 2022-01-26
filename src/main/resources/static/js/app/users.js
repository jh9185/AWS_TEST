var main = {
    init : function () {
        var _this = this;
        $('#btn-user-update').on('click', function () {
            _this.update();
        });
    },
    update : function () {
        var data = {
            name: $('#name').val(),
            email:$('#email').val(),
            region: $('#region').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/user/update/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();
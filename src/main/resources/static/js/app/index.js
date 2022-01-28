var main = {
    init : function () {
        var _this = this;
        $('#btn-index-bus-update').on('click', function () {
            _this.update();
        });
    },
    update : function () {

        $.ajax({
            type: 'GET',
            url: '/bus/update/',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('버스 정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();
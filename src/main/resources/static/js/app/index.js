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

function favoriteSave(region, name, number) {
    var data = {
        region: region,
        name: name,
        number: number
    };

    $.ajax({
        type: 'POST',
        url: '/bus/favorite/save',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function () {
        alert('즐겨찾기에 추가되었습니다.');
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function favoriteDelete(region, name, number) {
    var data = {
        region: region,
        name: name,
        number: number
    };

    $.ajax({
        type: 'DELETE',
        url: '/bus/favorite/delete',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function () {
        alert('즐겨찾기에서 삭제되었습니다.');
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

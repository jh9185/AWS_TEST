$(document).ready(function() {
        let upFile = $('#upFile')[0];
        upFile.addEventListener('change', function() {
            let form = $('#uploadFrm')[0];
            let frmData = new FormData(form);

        $.ajax({
            enctype: "multipart/form-data",
            type: "POST",
            url : "/bus/fileDBUpload",
            processData: false,
            contentType: false,
            cache: false,
            data: frmData,
            success: function(data) {
                console.log(data);
            },
            error: function(e) {
                console.log(e);
                alert('파일업로드 실패');
            }
        })
    });
});
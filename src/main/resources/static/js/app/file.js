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
                if(data == "OK"){
                    alert("파일 업로드가 완료되었습니다.");
                    window.location.href = '/';
                }else{
                    alert("파일 업로드에 실패했습니다.");
                }
                console.log(data);
            },
            error: function(e) {
                console.log(e);
                alert('파일업로드 실패');
            }
        })
    });
});
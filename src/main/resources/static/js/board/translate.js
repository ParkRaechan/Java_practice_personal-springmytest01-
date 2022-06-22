
function process() {

    $.ajax({
        url: "https://dapi.kakao.com/v2/translation/translate",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        headers: {
            "Authorization": "KakaoAK 4f6cc21e2479c0a457c9b2e760f034eb"
        },
        data: $("#transExForm").serialize(),
        success: function(data) {
            $("#result_translation").val(data.translated_text);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var errorMsg = "Ready Status: ";
            errorMsg += jqXHR.readyState + "\n";
          	errorMsg += "Status Text:";
          	errorMsg += jqXHR.readystatusText + "\n";
            alert(errorMsg);
        }
    })
}


function registration(){
        $.ajax({
            url: "/board/registration",
            method: "POST",
            data : { "rrs11" : $("#result_translation").val(),"whoqwe":$("#whoqwe").text() } ,
            success: function( re ){
                    alert( re );
                    if( re == true ){
                        alert("successed");
                        location.reload(); // 리로드
                    }else{
                        alert("failed");
                    }
            }
        });
}

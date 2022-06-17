function login(){
    $.ajax({
        url: "/member/login",
        method: "POST",
        data : { "mid" : $("#mid").val()    , "mpassword" : $("#mpassword").val()   } ,
        success: function( re ){
                alert( re );
                if( re == true ){
                    alert("successed");
                    location.href = "/"; // 메인페이지로 매핑
                }else{
                    alert("failed");
                }
        }
    });
}
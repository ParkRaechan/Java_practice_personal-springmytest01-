board_list(0,"","");
///시작함수




///전역변수
let current_page = 0;
let current_key = "";
let current_keyword = "";
///////




function board_list( page , key , keyword   ){

        this.current_page = page;
        if( key != undefined ) { this.current_key = key; }
        if( keyword != undefined ){ this.current_keyword = keyword; }

        $.ajax({
            url : "/board/getboardlist",
            data:{"key" :  this.current_key  , "keyword" : this.current_keyword , "page" :  this.current_page },
            method : "GET",
            success : function( boardlist ){

                 let html = '<tr> <th width="10%">no</th><th width="50%">content</th><th width="40%">name</th></tr>';

                if( boardlist.data.length == 0 ){ // 검색 결과가 존재하지 않으면
                          html +=
                                '<tr>'+
                                        '<td colspan="5">No result</td> '+
                                 '</tr>';
                }else{
                        for( let i = 0 ; i<boardlist.data.length ; i++ ){
                            html +=
                                    '<tr>'+
                                            '<td>'+boardlist.data[i].bno+'</td> '+
                                            '<td>'+boardlist.data[i].bcontent+'</td> '+
                                            '<td>'+boardlist.data[i].whoqwe+'</td>'+
                                     '</tr>';
                        }
                 }
//////////////////////////////////////////////////////////////////////////////////////// 페이징 버튼 생성 코드 ///////////////////////////////////////////////////////////////////////
                 let pagehtml = "";
                 ////////////////////////////////////////////  이전 버튼 ////////////////////////////////////////////////
                 if( page == 0 ){   // 현재 페이지가 첫페이지 이면
                        pagehtml +=
                         '<li class="page-item"> '+
                                     '<button class="page-link" onclick="board_list('+ (page)  +')"> 이전 </button>'+  // 검색 없음
                          '</li>';
                 }else{  // 현재 페이지가 첫페이지가 아니면
                     pagehtml +=
                        '<li class="page-item"> '+
                                    '<button class="page-link" onclick="board_list('+ (page-1)  +')"> 이전 </button>'+  // 검색 없음
                         '</li>';
                  }
                 ////////////////////////////////////////////  ////////////////////////////////// ////////////////////////////////////////////////
                ////////////////////////////////////////// 가운데에 들어가는 페이징 버튼수 //////////////////////////////////////////
                 for( let i = boardlist.startbtn ; i<=boardlist.endhtn ; i++ ){
                    pagehtml +=
                          '<li class="page-item"> '+
                            '<button class="page-link" onclick="board_list('+(i-1)+')"> '+i+' </button>'+  // 검색 없음
                          '</li>';
                 }
                ///////////////////////////////////////// ///////////////////////////////////////  //////////////////////////////////////////
                ////////////////////////////////////////////  다음 버튼 ////////////////////////////////////////////////
                if( page == boardlist.totalpages -1 ){ // 현재 페이지가 마지막 페이지이면
                     pagehtml +=
                            '<li class="page-item"> '+
                                        '<button class="page-link" onclick="board_list('+ (page)  +')"> 다음 </button>'+  // 검색 없음
                             '</li>';
                }else{ // 아니면
                     pagehtml +=
                        '<li class="page-item"> '+
                                    '<button class="page-link" onclick="board_list('+ (page+1)  +')"> 다음 </button>'+  // 검색 없음
                         '</li>';
                }

                ////////////////////////////////////////////  ////////////// ////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////  ///////////////////////////////////////////////////////////////////////

                $("#boardtable").html( html ); // 테이블에 html  넣기
                $("#pagebtnbox").html( pagehtml); // 페이징버튼 html 넣기

            }
        });
}



function search(){
    let key = $("#key").val();
    let keyword = $("#keyword").val();
    board_list(  0 ,  key , keyword );
}

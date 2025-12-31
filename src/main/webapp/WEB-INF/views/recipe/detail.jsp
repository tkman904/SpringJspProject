<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 960px;
}

p {
	overflow: hidden; 
	white-space: nowrap;
	text-overflow: ellipsis;
}

.nav-link {
	cursor: pointer;
}
</style>
<script>
const SESSION_ID = '${sessionScope.id}'
</script>
</head>
<body>
  <div class="container" style="margin-top: 10px;">
    <div class="row text-right">
      <c:if test="${sessionScope.id == null}">
        <a href="/member/login" class="btn btn-sm btn-primary">로그인</a>
      </c:if>
      <c:if test="${sessionScope.id != null}">
        <a href="/member/logout" class="btn btn-sm btn-danger">로그아웃</a>
      </c:if>
    </div>
  </div>
  <div class="container" id="recipe_detail">
    <div class="row">
      <table class="table">
        <tbody>
          <tr>
            <td class="text-center" colspan="3">
              <img :src="store.detail.vo.poster" style="width: 800px; height: 350px;">
            </td>
          </tr>
          <tr>
            <td class="text-center" colspan="3">
              <h3 class="text-center">{{store.detail.vo.title}}</h3>
            </td>
          </tr>
          <tr>
            <td class="text-center" colspan="3">{{store.detail.vo.content}}</td>
          </tr>
          <tr>
          	<td class="text-center"><img src="/images/a1.png"></td>
          	<td class="text-center"><img src="/images/a2.png"></td>
          	<td class="text-center"><img src="/images/a3.png"></td>
          </tr>
          <tr>
          	<td class="text-center">{{store.detail.vo.info1}}</td>
          	<td class="text-center">{{store.detail.vo.info2}}</td>
          	<td class="text-center">{{store.detail.vo.info3}}</td>
          </tr>
          <tr>
            <td class="text-right" colspan="3">
              <button type="button" class="btn-sm btn-primary">예약(FullCalendar)</button>
              <button type="button" class="btn-sm btn-danger" onclick="javascript:history.back()">목록</button>
            </td>
          </tr>
        </tbody>
      </table>
      <table class="table">
        <tbody>
          <tr>
            <td colspan="2"><h3>[조리순서]</h3></td>
          </tr>
          <tr v-for="(data, index) in store.detail.tList">
            <td class="text-left" width="85%">{{data}}</td>
            <td class="text-right" width="15%"><img :src="store.detail.iList[index]" style="width: 180px; height: 120px;"></td>
          </tr>
        </tbody>
      </table>
      <table class="table">
        <tbody>
          <tr>
            <td colspan="2"><h3>[레시피 작성자]</h3></td>
          </tr>
          <tr>
            <td width="20%" rowspan="2" class="text-center">
              <img :src="store.detail.vo.chef_poster" style="width: 100px; height: 100px;" class="img-circle">
            </td>
            <td width="80%">{{store.detail.vo.chef}}</td>
          </tr>
          <tr>
            <td width="80%">{{store.detail.vo.chef_profile}}</td>
          </tr>
          
        </tbody>
      </table>
    </div>
  <div class="row" style="margin-top: 20px;" id="recipe_reply">
    <table class="table">
      <tbody>
        <tr>
          <td>
            <table class="table" v-for="(rvo, index) in rStore.reply_list" :key="index">
              <tbody>
                <tr>
                  <td class="text-left" width="80%">♣{{rvo.name}}&nbsp;{{rvo.dbday}}</td>
                  <td class="text-right" width="20%">
                    <button class="btn-xs btn-success" v-if="rStore.sessionId === rvo.id" @click="rStore.toggleUpdate(rvo.no, rvo.msg)">
                      {{rStore.upReplyNo === rvo.no ? '취소' : '수정'}}
                    </button>
                    <button class="btn-xs btn-warning" v-if="rStore.sessionId === rvo.id" @click="rStore.replyDelete(rvo.no)">삭제</button>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" class="text-left">
                    <pre style="white-space: pre-wrap;">{{rvo.msg}}</pre>
                  </td>
                </tr>
                <tr>
		          <td colspan="2" class="text-left" v-if="rStore.upReplyNo === rvo.no">
		            <textarea rows="5" cols="100" style="float: left;" v-model="rStore.updateMsg[rvo.no]"></textarea>
		            <button type="button" class="btn-success" style="width: 100px; height: 106px; float: left;" @click="rStore.replyUpdate(rvo.no)">댓글 수정</button>
		          </td>
		        </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="table" v-if="rStore.sessionId">
      <tbody>
        <tr>
          <td>
            <textarea rows="5" cols="100" style="float: left;" v-model="rStore.msg"></textarea>
            <button type="button" class="btn-success" style="width: 100px; height: 106px; float: left;" @click="rStore.replyInsert()">댓글 작성</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
  <script src="/js/axios.js"></script>
  <script src="/js/recipeStore.js"></script>
  <script src="/js/replyStore.js"></script>
  <script>
    const { createApp, onMounted } = Vue
    const { createPinia } = Pinia
    const detailApp = createApp({
    	setup() {
    		const store = useRecipeStore()
    		const rStore = useReplyStore()
    		const params = new URLSearchParams(location.search)
   			const no = params.get('no')
   			
   			onMounted(()=> {
   				store.recipeDetailData(no)
   				rStore.sessionId = SESSION_ID
   				rStore.replyListData(no)
   			})
   			
   			return {
    			store,
    			rStore
    		}
    	}
    })
    detailApp.use(createPinia())
    detailApp.mount('#recipe_detail')
  </script>
</body>
</html>
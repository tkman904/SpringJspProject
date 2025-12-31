<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 300px;
}
</style>
</head>
<body>
  <div class="container">
    <h3 class="text-center">로그인</h3>
    <div class="row">
      <table class="table">
        <tbody>
          <tr>
            <th width="20%">ID</th>
            <td width="80%">
              <input type="text" ref="idRef" size="20" class="input-sm" v-model="store.id">
            </td>
          </tr>
          <tr>
            <th width="20%">PW</th>
            <td width="80%">
              <input type="password" ref="pwdRef" size="20" class="input-sm" v-model="store.pwd">
            </td>
          </tr>
          <tr>
            <td class="text-center" colspan="2">
              <button type="button" class="btn-sm btn-success" @click="store.login(idRef, pwdRef)">로그인</button>&nbsp;
              <button type="button" class="btn-sm btn-warning" onclick="javascript:history.back()">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <script src="/js/axios.js"></script>
  <script src="/js/memberStore.js"></script>
  <script>
    const { createApp, onMounted, ref } = Vue
    const { createPinia } = Pinia
    const logApp = createApp({
    	setup() {
    		const store = useMemberStore()
    		const idRef = ref(null)
    		const pwdRef = ref(null)
    		
    		return {
    			store,
    			idRef,
    			pwdRef
    		}
    	}
    })
    logApp.use(createPinia())
    logApp.mount('.container')
  </script>
</body>
</html>
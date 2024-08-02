<script setup>
import router from "@/router";
import {ref} from "vue";

const search = ref("");
const isAuthenticated = ref(!!sessionStorage.getItem('authHeader'));

const goHome = function () {
    router.push("/");
}

const goLogin = function () {
    router.push("/login");
}

const goSignup = function () {
    router.push("/signup");
}

const goMy = function () {
    router.push("/my");
}

const logout = function () {
    sessionStorage.removeItem('authHeader');
    window.location.reload();
}

const goQuestions = function () {
    router.push({name: 'Home', query: {search: search.value}})
}

</script>

<template>
    <div class="header-wrap">
        <div class="header-left">
            <a href="" @click="goHome">
                <h1>I DON'T KNOW</h1>
                <img src="@/assets/logo.png" alt="메인 페이지로 이동">
            </a>
            <input type="text" class="header-search" v-model="search" @keyup.enter="goQuestions">
        </div>
        <div class="header-right" v-if="!isAuthenticated">
            <button class="go-signup-button" @click = "goSignup">회원가입</button>
            <button class="go-login-button" @click = "goLogin">로그인</button>
        </div>
        <div class="header-right" v-if="isAuthenticated">
            <button class="go-signup-button" @click = "goMy">마이페이지</button>
            <button class="go-login-button" @click = "logout">로그아웃</button>
        </div>
    </div>
</template>

<style scoped>
.header-wrap {
    width: 70vw;
    height: 100px;
    display: flex;
    align-items: center;
}

.header-left {
    display: flex;
    align-items: center;
    width: 850px;
}

.header-left a{
    display: flex;
    align-items: center;
}

/*숨기기*/
h1 {
    position: absolute;
    width: 1px;
    height: 1px;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
}

.header-left input {
    max-width: 525px;
    width: 100%;
    height: 45px;
    border: 5px solid #333A73;
    border-radius: 20px;
    padding-left: 50px;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 16px;
    margin-left: auto;
    background-image: url("@/assets/search.png");
    background-repeat: no-repeat;
    background-position: 10px center;
    background-size: 25px;
}

.header-right {
    display: flex;
    flex: 1;
    justify-content: end;
}

.go-signup-button {
    width: 120px;
    height: 45px;
    border-radius: 50px;
    border: 1px solid #333A73;
    box-shadow: 0 0 5px #000000;
    background-color: #333A73;
    color: #FFFFFF;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 16px;
}

.go-login-button {
    width: 120px;
    height: 45px;
    border-radius: 50px;
    border: 1px solid #FFFFFF;
    box-shadow: 0 0 5px #000000;
    background-color: #FFFFFF;
    color: #000000;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 16px;
    margin-left: 20px;
}
</style>

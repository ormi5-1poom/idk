<script setup>
import router from "@/router";

const goLogin = function () {
    router.push("/login");
}

const goHome = function () {
    router.push("/");
}
</script>

<template>
    <div class="signup-wrap">
        <div class="signup-title" @click="goHome">
            <span>나 진짜 아무것도 모르겠어</span>
            <h2>I DON'T KNOW</h2>
        </div>
        <div class="signup-form">
            <input type="email" class="signup-email" placeholder="이메일">
            <input type="password" class="signup-password" placeholder="비밀번호">
            <input type="password" class="signup-password-check" placeholder="비밀번호 확인">
            <input type="text" class="signup-nickname" placeholder="닉네임">

            <button>가입하기</button>
        </div>
        <div class="go-login-button" @click="goLogin">
            <img src="@/assets/signup.png" alt="">
            <span>이미 계정이 있으신가요?</span>
        </div>
    </div>
</template>

<style scoped>
.signup-wrap {
    width: 500px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 133px;
}

.signup-title {
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: pointer;
}

.signup-title span {
    font-family: 'Nexon Light', sans-serif;
    font-size: 15px;
    color: #C5CCD2;
    letter-spacing: 1px;
    margin-bottom: 10px;
}
.signup-title h2 {
    font-family: 'Gmarket Bold', sans-serif;
    font-size: 50px;
    color: #333A73;
}

.signup-form {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 25px 0 0 0;
}

.signup-email {
    width: 450px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-radius: 10px 10px 0 0;
    padding-left: 50px;
    font-family: 'Nexon Regular', sans-serif;
    font-size: 16px;
    background-image: url("@/assets/email.png");
    background-repeat: no-repeat;
    background-position: 20px center;
}
.signup-email::placeholder, .signup-password::placeholder, .signup-password-check::placeholder, .signup-nickname::placeholder {
    font-size: 15px;
    color: #C5CCD2;
    font-family: 'Nexon Regular', sans-serif;
}

.signup-password, .signup-password-check {
    width: 450px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-top: 0;
    padding-left: 50px;
    font-family: 'Nexon Light', sans-serif;
    font-size: 16px;
    background-image: url("@/assets/password.png");
    background-repeat: no-repeat;
    background-position: 20px center;
}

.signup-nickname {
    width: 450px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-top: 0;
    border-radius: 0 0 10px 10px;
    padding-left: 50px;
    font-family: 'Nexon Light', sans-serif;
    font-size: 16px;
    background-image: url("@/assets/nickname.png");
    background-repeat: no-repeat;
    background-position: 20px center;
}
.signup-email:focus, .signup-password:focus, .signup-password-check:focus, .signup-nickname:focus {
    outline: none;
    border: 3px solid #333A73;
}

.signup-form button {
    background-color: #333A73;
    width: 450px;
    height: 50px;
    color: #FFFFFF;
    font-family: 'Nexon Bold', sans-serif;
    font-size: 16px;
    border-radius: 10px;
    margin: 25px 0;
    border: 1px solid #333A73;
}

.go-login-button {
    cursor: pointer;
}

.go-login-button span{
    font-family: 'Nexon Medium', sans-serif;
    font-size: 15px;
    color: #888888;
    margin-left: 5px;
}
</style>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "axios";

const username = ref("");
const password = ref("");

const goSignup = function () {
    router.push("/signup");
}

const goHome = function () {
    router.push("/");
}

const login = async function () {
    try {
        const credentials = btoa(`${username.value}:${password.value}`);
        const authHeader = `Basic ${credentials}`;

        const response = await axios.post('/api/users/login', null, {
            headers: {
                'Authorization': authHeader
            }
        });
        console.log('Login successful: ', response.data);
        sessionStorage.setItem('authHeader', authHeader);
        await router.replace('/');
    } catch (error) {
        alert("회원정보가 올바르지 않습니다.");
        console.log('Login failed: ', error);
        console.log(username.value)
        console.log(password.value)
    }
}

onMounted(() => {
    const inputs = document.querySelectorAll('.login-email, .login-password');

    inputs.forEach(input => {
        input.addEventListener('focus', () => {
            input.dataset.placeholder = input.placeholder;
            input.placeholder = '';
        });

        input.addEventListener('blur', () => {
            if (input.dataset.placeholder !== undefined) {
                input.placeholder = input.dataset.placeholder;
                delete input.dataset.placeholder;
            }
        });
    });

    document.querySelector('.login-email').focus();
})

</script>

<template>
    <div class="login-wrap">
        <div class="login-title" @click="goHome">
            <span>나 진짜 아무것도 모르겠어</span>
            <h2>I DON'T KNOW</h2>
        </div>
        <div class="login-form" @keyup.enter="login">
            <input type="email" class="login-email" placeholder="이메일" v-model="username">
            <input type="password" class="login-password" placeholder="비밀번호" v-model="password">
            <button @click="login">로그인</button>

        </div>
        <div class="go-signup-button" @click="goSignup">
            <img src="@/assets/signup.png" alt="">
            <span>회원가입</span>
        </div>
    </div>
</template>

<style scoped>
.login-wrap {
    width: 500px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 200px;
}

.login-title {
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: pointer;
}

.login-title span {
    font-family: 'Nexon Light', sans-serif;
    font-size: 15px;
    color: #C5CCD2;
    letter-spacing: 1px;
    margin-bottom: 10px;
}
.login-title h2 {
    font-family: 'Gmarket Bold', sans-serif;
    font-size: 50px;
    color: #333A73;
}

.login-form {
    width: 100%;
    border-radius: 15px;
    border: 1px solid #C5CCD2;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 25px 0;
}

.login-email {
    width: 450px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-radius: 10px 10px 0 0;
    margin-top: 25px;
    padding-left: 50px;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 16px;
    background-image: url("@/assets/email.png");
    background-repeat: no-repeat;
    background-position: 20px center;
}
.login-email::placeholder, .login-password::placeholder {
    font-size: 15px;
    color: #C5CCD2;
    font-family: 'Nexon Medium', sans-serif;
}

.login-password {
    width: 450px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-top: 0;
    border-radius: 0 0 10px 10px;
    padding-left: 50px;
    font-family: 'Nexon Light', sans-serif;
    font-size: 16px;
    background-image: url("@/assets/password.png");
    background-repeat: no-repeat;
    background-position: 20px center;
}
.login-email:focus, .login-password:focus {
    outline: none;
    border: 3px solid #333A73;
}

.login-form button {
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

.go-signup-button {
    cursor: pointer;
}

.go-signup-button span{
    font-family: 'Nexon Medium', sans-serif;
    font-size: 15px;
    color: #888888;
    margin-left: 5px;
}

</style>

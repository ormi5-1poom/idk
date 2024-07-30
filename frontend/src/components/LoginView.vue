<template>
    <div class="login-wrap">
        <div class="login-title">
            <span>나 진짜 아무것도 모르겠어</span>
            <h2>I DON'T KNOW</h2>
        </div>
        <div class="login-form">
            <input type="email" class="login-email" placeholder="이메일">
            <input type="password" class="login-password" placeholder="비밀번호">
            <button>로그인</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'loginView',
    components: {

    }
}

document.addEventListener('DOMContentLoaded', () => {
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
});
</script>



<style scoped>
.login-wrap {
    width: 460px;
    height: 400px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.login-title span {
    font-family: 'Nexon Light', sans-serif;
    font-size: 15px;
    color: #C5CCD2;
    letter-spacing: 1px;
}
.login-title h2 {
    font-family: 'Gmarket Bold', sans-serif;
    font-size: 48px;
    color: #333A73;
}

.login-form {
    width: 100%;
    height: 260px;
    border-radius: 15px;
    border: 1px solid #C5CCD2;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
}

.login-email {
    width: 410px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-radius: 10px 10px 0 0;
    margin-top: 25px;
    padding-left: 20px;
    font-family: 'Nexon Regular', sans-serif;
    font-size: 16px;
}
.login-email::placeholder, .login-password::placeholder {
    font-size: 13px;
}

.login-password {
    width: 410px;
    height: 60px;
    border: 1px solid #C5CCD2;
    border-top: 0;
    border-radius: 0 0 10px 10px;
    padding-left: 20px;
    font-family: 'Nexon Light', sans-serif;
    font-size: 16px;
}
.login-email:focus, .login-password:focus {
    outline: none;
    border: 3px solid #333A73;
}


.login-form button {
    background-color: #333A73;
    width: 410px;
    height: 50px;
    color: #FFFFFF;
    font-family: 'Nexon Bold', sans-serif;
    font-size: 16px;
    border-radius: 10px;
    margin-top: 30px;
}

</style>

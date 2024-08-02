<script setup>
import {ref} from "vue";
import router from "@/router";
import {createQuestionAPI} from "@/api";

const title = ref("");
const content = ref("");

const handleInput = (event) => {
    const target = event.currentTarget;
    const maxlength = target.getAttribute("maxlength");
    if (target.value.length > maxlength) {
        target.value = target.value.slice(0, maxlength);
    }
}

const goPre = function () {
    router.go(-1);
}

const createQuestion = async function () {
    try {
        const createQuestionRequest = {
            title: title.value,
            content: content.value
        }
        const response = await createQuestionAPI(createQuestionRequest);
        await router.push(`/question/${response.data.id}`);
    } catch (error) {
        console.log(error);
        alert("로그인이 필요한 서비스입니다.");
        await router.push('/login');
    }
}
</script>

<template>
    <div class="write-wrap">
        <div class="write-title">
            <img src="@/assets/Q.png" alt="">
            <input type="text" class="question-title" v-model="title" placeholder="제목을 입력해 주세요." minlength="2" maxlength="50" @input="handleInput">
        </div>
        <div class="write-content">
            <textarea name="question-content" v-model="content" placeholder="내용을 입력해 주세요." @input="handleInput" maxlength="500"></textarea>
            <strong>저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 관리자에 의해 제재를 받으실 수 있습니다.</strong>
        </div>
        <div class="write-button">
            <button class="go-pre-button" @click="goPre">뒤로가기</button>
            <button class="submit-button" @click="createQuestion">작성하기</button>
        </div>
    </div>
</template>

<style scoped>
.write-wrap {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 120px;
}
.write-title {
    width: 100%;
    display: flex;
    align-items: center;
    margin-bottom: 50px;
}
.question-title {
    width: 100%;
    border: 5px solid #333A73;
    border-radius: 50px;
    padding-left: 50px;
    height: 100px;
    display: flex;
    margin-left: 30px;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    color: #757575;
}
.question-title::placeholder {
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    color: #757575;
}
.write-content {
    background-color: #EBEBEB;
    border-radius: 15px;
    border: none;
    width: 100%;
    height: 600px;
    padding: 50px;
    margin-bottom: 80px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.write-content strong{
    font-family: 'Nexon Medium', sans-serif;
    font-size: 20px;
    color: #000000;
    flex: 1;
    align-content: end;
}
textarea {
    resize: none;
    background-color: #EBEBEB;
    border: none;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    width: 100%;
    height: 400px;
    color: #757575;
    line-height: 40px;
    column-count: 10;
    overflow: hidden;
}
textarea:focus {
    outline: none;
}
.go-pre-button {
    width: 160px;
    height: 75px;
    border-radius: 50px;
    border: 1px solid #FFFFFF;
    box-shadow: 0 0 5px #000000;
    background-color: #FFFFFF;
    color: #000000;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    margin-right: 40px;
}
.submit-button {
    width: 160px;
    height: 75px;
    border-radius: 50px;
    border: 1px solid #333A73;
    box-shadow: 0 0 5px #000000;
    background-color: #333A73;
    color: #FFFFFF;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
}
</style>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import {editNoticeAPI, getNoticeAPI} from "@/api";
import {useRoute} from "vue-router";

const title = ref("");
const content = ref("");
const noticeId = ref(null);

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

const getNotice = async function (id) {
    try {
        const response = await getNoticeAPI(id);
        return response.data;
    } catch (error) {
        console.log(error);
    }
}

const editNotice = async function () {
    try {
        const editNoticeRequest = {
            title: title.value,
            content: content.value
        }
        await editNoticeAPI(noticeId.value, editNoticeRequest);
        await router.push(`/notice/${noticeId.value}`);
    } catch (error) {
        console.log(error);
    }
};

onMounted(async () => {
    noticeId.value = useRoute().params.noticeId;
    const response = await getNotice(noticeId.value);
    title.value = response.title;
    content.value = response.content;
})
</script>

<template>
    <div class="edit-wrap">
        <div class="edit-title">
            <img src="@/assets/N2.png" alt="">
            <input type="text" class="question-title" v-model="title" placeholder="제목을 입력해 주세요." minlength="2" maxlength="50" @input="handleInput">
        </div>
        <div class="edit-content">
            <textarea name="question-content" v-model="content" placeholder="내용을 입력해 주세요." @input="handleInput" maxlength="500"></textarea>
        </div>
        <div class="edit-button">
            <button class="go-pre-button" @click="goPre">뒤로가기</button>
            <button class="submit-button" @click="editNotice">수정하기</button>
        </div>
    </div>
</template>

<style scoped>
.edit-wrap {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 120px;
}
.edit-title {
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
.edit-content {
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

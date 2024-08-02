<script setup>
import {deleteNoticeAPI, getNoticeAPI} from "@/api";
import {useRoute} from "vue-router";
import {computed, onMounted, ref} from "vue";
import router from "@/router";

const notice = ref(null);

const getNotice = async function () {
    try {
        const response = await getNoticeAPI(useRoute().params.noticeId);
        return response.data;
    } catch (error) {
        console.log(error);
    }
}

const goEditNotice = function () {
    router.push(`/notice/${notice.value.id}/edit`);
};

const deleteNotice = async function () {
    try {
        if (confirm("정말 삭제하시겠습니까?")) {
            await deleteNoticeAPI(notice.value.id);
            await router.push('/notice');
        }
    } catch (error) {
        console.log(error);
    }

};

const formatCreatedAt = computed(() => {
    const createdAt = new Date(notice.value.createdAt);
    return createdAt.toISOString().split('T')[0];
});
onMounted(async () => {
    notice.value = await getNotice();


})
</script>

<template>
    <div class="notice-wrap" v-if="notice">
        <div>
            <h2>공지사항</h2>
        </div>
        <div class="notice-top">
            <img src="@/assets/N.png" alt="">
            <div class="notice-text">
                <span class="notice-title">{{notice.title}}</span>
                <div>
                    <span class="notice-info">{{notice.writer.nickname}} | {{formatCreatedAt}} | 조회수 {{notice.views}}</span>
                </div>
            </div>
        </div>
        <div class="notice-content">
            <pre>{{notice.content}}</pre>
        </div>
        <div class="notice-button">
            <button class="go-edit-button" @click="goEditNotice">수정하기</button>
            <button class="delete-button" @click="deleteNotice">삭제하기</button>
        </div>
    </div>
</template>

<style scoped>
.notice-wrap {
    width: 100%;
    margin-top: 100px;
}
h2 {
    font-family: 'Gmarket Bold', sans-serif;
    font-size: 47px;
    color: #000000;
    margin-bottom: 35px;
    padding-left: 35px;
}
.notice-top {
    display: flex;
    border-top: 3px solid #000000;
    border-bottom: 1px solid #000000;
    padding: 35px;
}
.notice-title {
    font-family: 'Nexon Medium', sans-serif;
    font-size: 45px;
    color: #000000;
}
.notice-info {
    font-family: 'Gmarket Regular', sans-serif;
    font-size: 20px;
    color: #000000;
}
.notice-text {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
img {
    margin-right: 30px;
}
pre {
    margin-top: 60px;
    padding-left: 35px;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    color: #000000;
    min-height: 400px;
}
.notice-button {
    display: flex;
    justify-content: end;
}
.go-edit-button {
    width: 160px;
    height: 60px;
    border-radius: 50px;
    border: 1px solid #FFFFFF;
    box-shadow: 0 0 5px #000000;
    background-color: #FFFFFF;
    color: #000000;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
    margin-right: 25px;
}
.delete-button {
    width: 160px;
    height: 60px;
    border-radius: 50px;
    border: 1px solid #333A73;
    box-shadow: 0 0 5px #000000;
    background-color: #333A73;
    color: #FFFFFF;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 25px;
}
</style>

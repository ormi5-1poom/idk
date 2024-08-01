<script setup>
import router from "@/router";

const goQuestion = function () {
    router.push("/question/1");
}
const goNotice = function () {
    router.push("/notice/1");
}
</script>

<template>
    <div>
        메인 페이지(첫 화면)
        <button @click="goQuestion()">질문 상세</button>
        <button @click="goNotice()">공지사항 상세</button>
    </div>
</template>

<style scoped>

</style>

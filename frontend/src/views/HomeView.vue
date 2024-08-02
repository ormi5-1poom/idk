<script setup>
import router from "@/router";
import {getQuestionsAPI} from "@/api";
import {onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";

const route = useRoute();
const questions = ref([]);
let isLoading = ref(false);

const goQuestion = function (id) {
    router.push(`/question/${id}`);
}
const goNotice = function () {
    router.push("/notice/1");
}
const getQuestions = async function (query) {
    if (isLoading.value) return;
    isLoading.value = true;
    try {
        if (query === undefined) query = '';
        const getQuestionsRequest = {
            title: query,
            size: 10,
            page: 0
        }
        const response = await getQuestionsAPI(getQuestionsRequest);
        questions.value = response.data.content;
    } catch (error) {
        console.log(error);
    }
}

onMounted(() => {
    getQuestions(route.query.search);
})

watch(async () => route.query.search, (newTitle) => {
    getQuestions(newTitle);
})
</script>

<template>
    <div>
        메인 페이지(첫 화면)
        <button @click="goNotice()">공지사항 상세</button>
        <div>
            <ul v-for="question in questions" :key="question.id">
                <li>
                    <div @click="goQuestion(question.id)">
                        {{question.writer.nickName}}
                        {{question.title}}
                        {{question.content}}
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>
li div {
    cursor: pointer;
}
</style>

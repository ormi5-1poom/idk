<script setup>
import {getQuestionAPI} from "@/api";
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";

const question = ref(null);

const getQuestion = async function () {
    try {
        const response = await getQuestionAPI(useRoute().params.questionId);
        return response.data;

    } catch (error) {
        console.log(error);
    }
}

onMounted(async () => {
    question.value = await getQuestion();
})
</script>

<template>
    <div v-if="question">
        <p>작성자: {{question.writer.nickName}}</p>
        <p>제목: {{question.title}}</p>
        <pre>내용: {{question.content}}</pre>
        <hr>
        <ul v-for="answer in question.answers" :key="answer.id">
            <li>
                <div>
                    <p>{{answer.writer.nickName}}</p>
                    <pre>{{answer.content}}</pre>
                </div>
            </li>
        </ul>
    </div>
</template>

<style scoped>

</style>

<script setup>
import {deleteQuestionAPI, getQuestionAPI} from "@/api";
import {useRoute} from "vue-router";
import {computed, nextTick, onMounted, ref, watch} from "vue";
import router from "@/router";

const question = ref(null);
const content = ref("");

const getQuestion = async function () {
  try {
    const response = await getQuestionAPI(useRoute().params.questionId);
    return response.data;

  } catch (error) {
    console.log(error);
  }
}

const autoResize = function () {
    const textarea = document.querySelector('textarea');
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
};
watch(content, () => {
    nextTick(() => {
        autoResize();
    })
})

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toISOString().split('T')[0];
};

const deleteQuestion = async function () {
    try {
        if (confirm("정말 삭제하시겠습니까?")) {
            await deleteQuestionAPI(question.value.id);
            await router.push('/');
        }
    } catch (error) {
        console.log(error);
    }
}

const formatCreatedAt = computed(() => {
  return question.value ? formatDate(question.value.createdAt) : '';
});
onMounted(async () => {
  question.value = await getQuestion();
})
</script>

<template>
  <div class="question-wrap" v-if="question">
    <div>
      <h2>궁금해요</h2>
      <div class="question-top">
        <img src="@/assets/Q.png" alt="">
        <div class="contents-text">
          <span class="contents-title">{{ question.title }}</span>
          <div>
          <span class="question-info">
            {{ question.writer.nickName }} | {{ formatCreatedAt }} | 조회수 {{ question.views }}
          </span>
          </div>
        </div>
      </div>
      <pre class="question-contents">{{ question.content }}</pre>
      <div class="question-button">
        <button class="go-edit-button">수정하기</button>
        <button class="blue-button" @click="deleteQuestion">삭제하기</button>
      </div>
      <hr>

      <div class="answer-top">
        <img src="@/assets/A.png" alt="">
        <div class="contents-text">
          <span class="contents-title">총 {{ question.answerCount }}개의 답변이 작성되었습니다.</span>
        </div>
      </div>
      <div class="write-wrap">
        <div class="write-answer">
        <textarea name="answer-content" v-model="content" placeholder="내용을 입력해 주세요."
                  maxlength="1000" @input="autoResize"></textarea>
          <div>
            <strong>저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 관리자에 의해 제재를 받으실 수 있습니다.</strong>
            <button class="blue-button">등록하기</button>
          </div>
        </div>
      </div>
      <ul v-for="answer in question.answers" :key="answer.id">
        <li :class="{ 'selected-answer': answer.selected }">
          <div class="contents-text">
            <div>
            <span class="answer-info">
            {{ answer.writer.nickName }} | {{ formatDate(answer.createdAt) }}
          </span>
              <button class="report-button" aria-label="신고하기">
                <img src="@/assets/report.png" alt="신고 아이콘">
              </button>
            </div>
            <p class="answer-contents">{{ answer.content }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.question-wrap {
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

.question-top {
  display: flex;
  border-top: 3px solid #000000;
  border-bottom: 1px solid #000000;
  padding: 35px;
}

.contents-title {
  font-family: 'Nexon Medium', sans-serif;
  font-size: 45px;
  color: #333A73;
  vertical-align: bottom;
  margin-top: 20px;
}

.question-info {
  font-family: 'Gmarket Regular', sans-serif;
  font-size: 20px;
  color: #000000;
}

.contents-text {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

img {
  margin-right: 30px;
  width: 120px;
  height: 135px;
}

.question-contents {
  font-family: 'Nexon Regular', sans-serif;
  font-size: 35px;
  color: #000000;
  margin-block: 80px;
  margin-left: 40px;
}

.question-button {
  display: flex;
  justify-content: end;
  margin-right: 5px;
  margin-bottom: 20px;
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

.blue-button {
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

.answer-top {
  margin-block: 45px;
  display: flex;
  padding: 35px;
  align-items: center;
  background-color: rgba(251, 168, 52, 0.27);
  border-radius: 20px;
}

.answer-top .contents-title {
  color: #263140;
}

ul {
  list-style-type: none;
  padding: 0;
  margin-top: 20px;
}

li {
  list-style-type: none; /* 추가: 점 제거 */
  margin-bottom: 40px;
  padding: 35px;
  border: 1px solid #b3b3b3;
  border-radius: 20px;
  background-color: #d9d9d9;
}

.selected-answer {
  background-color: #FFFFFF;
  border: 5px solid #FBA834;
}


.answer-info {
  font-family: "Nexon Light", sans-serif;
  font-size: 20px;
  color: #000000;
}

.answer-contents {
  font-family: "NEXON Lv1 Gothic OTF", sans-serif;
  font-size: 45px;
  color: #000000;
}

.contents-text div {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.report-button {
  background: none;
  border: none;
  cursor: pointer;
  padding: 0;
}

.report-button img {
  width: 31px;
  height: 31px;
}

.write-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 40px;
}

.write-answer {
  background-color: #FFFFFF;
  border-radius: 15px;
  border: 1px solid #000000;
  width: 100%;
  height: auto;
  padding: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.write-answer div {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.write-answer strong {
  font-family: 'Nexon Medium', sans-serif;
  font-size: 20px;
  color: #000000;
  flex: 1;
  align-self: end;
}

textarea {
  resize: none;
  background-color: #FFFFFF;
  border: none;
  font-family: 'Nexon Medium', sans-serif;
  font-size: 25px;
  width: 100%;
  height: auto;
    max-height: 400px;
  color: #000000;
  line-height: 40px;
  column-count: 10;
  overflow: hidden;
}

textarea:focus {
  outline: none;
}

</style>

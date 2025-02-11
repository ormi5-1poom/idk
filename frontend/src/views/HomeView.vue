<script setup>
import router from "@/router";
import {getNoticesTop5API, getQuestionsAPI} from "@/api";
import {computed, onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";

const route = useRoute();
const questions = ref([]);
const notices = ref([]);
let isLoading = ref(false);

const userRoles = computed(() => {
    const rolesString = sessionStorage.getItem('roles');
    return rolesString ? rolesString.split(' ') : [];
});

const isAdmin = computed(() => userRoles.value.includes('ADMIN'));
const isAnonymous = computed(() => userRoles.value.includes('ANONYMOUS'));
const isSuspend = computed(() => userRoles.value.includes('SUSPEND'));

// 날짜 yyyy-MM-dd 형식 출력
const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toISOString().split('T')[0];
};

const goQuestion = function (id) {
    router.push(`/question/${id}`);
}

const goQuestionWrite = function () {
    if (isAnonymous.value) {
        alert("로그인이 필요한 서비스입니다.");
        router.push(`/login`);
    } else {
        router.push(`/question/write`);
    }
};

const goNoticeList = function () {
    router.push("/notice");
}

const goNoticeDetail = function (id) {
    router.push(`/notice/${id}`);
}

const currentPage = ref(0);
const totalPages = ref(0);
const pageSize = 10;
const showPage = ref(10); // 한 번에 보여줄 페이지 수

const startPage = computed(() => {
    return Math.floor(currentPage.value / showPage.value) * showPage.value;
});

const endPage = computed(() => {
    const end = startPage.value + showPage.value - 1;
    return end >= totalPages.value ? totalPages.value - 1 : end;
});

const visiblePages = computed(() => {
    const pages = [];
    for (let i = startPage.value; i <= endPage.value; i++) {
        pages.push(i);
    }
    return pages;
});

const hasNextGroup = computed(() => {
    return endPage.value < totalPages.value - 1;
});

const hasPrevGroup = computed(() => {
    return startPage.value > 0;
});

const getQuestions = async function (query = '', page = 0) {
    if (isLoading.value) return;
    isLoading.value = true;
    try {
        const getQuestionsRequest = {
            title: query,
            size: pageSize,
            page: page
        }
        const response = await getQuestionsAPI(getQuestionsRequest);
        questions.value = response.data.content;
        totalPages.value = response.data.totalPages;
        currentPage.value = page;
    } catch (error) {
        console.log(error);
    } finally {
        isLoading.value = false;
    }
}

const changePage = (page) => {
    getQuestions(route.query.search, page);
}

const goToFirstPage = () => {
    changePage(0);
};

const goToLastPage = () => {
    changePage(totalPages.value - 1);
};

const goToNextGroup = () => {
    if (hasNextGroup.value) {
        changePage(endPage.value + 1);
    }
};

const goToPrevGroup = () => {
    if (hasPrevGroup.value) {
        changePage(startPage.value - 1);
    }
};

const getNoticesTop5 = async function () {
    try {
        const response = await getNoticesTop5API();
        notices.value = response.data;
    } catch (error) {
        console.log(error);
    }
};

onMounted(() => {
    getQuestions(route.query.search || ''); // 검색어가 없으면 빈 문자열 전달
    getNoticesTop5();
})

watch(() => route.query.search, (newTitle) => {
    getQuestions(newTitle || ''); // 검색어가 없으면 빈 문자열 전달
})
</script>

<template>
    <div class="wrap">
        <div class="question">
            <div class="question-write">
                <span>지금 이 순간 게시글</span>
                <a class="move" @click="goQuestionWrite" v-if="!isAdmin && !isSuspend">질문하러 가기 ></a>
            </div>

            <ul class="question-list" v-for="question in questions" :key="question.id">
                <li>
                    <div class="question-wrap" @click="goQuestion(question.id)">
                        <div class="question-writer">
                            <span>{{ question.writer.nickName }}</span>
                            <span>{{
                                    formatDate(question.createdAt)
                                }} | 조회수 {{ question.views }}</span>
                        </div>
                        <div class="question-title">
                            <span class="move question-title1">{{ question.title }}</span>
                            <span class="question-title2">{{ question.answerCount }}개의 답변</span>
                        </div>
                        <hr>
                    </div>
                </li>
            </ul>
            <div class="pagination">
                <button
                    @click="goToFirstPage"
                    :disabled="currentPage === 0"
                    class="pagination-button"
                >
                    &lt;&lt;
                </button>
                <button
                    @click="goToPrevGroup"
                    :disabled="!hasPrevGroup"
                    class="pagination-button"
                >
                    &lt;
                </button>
                <button
                    v-for="page in visiblePages"
                    :key="page"
                    @click="changePage(page)"
                    :class="{ active: currentPage === page }"
                    class="pagination-button"
                >
                    {{ page + 1 }}
                </button>
                <button
                    @click="goToNextGroup"
                    :disabled="!hasNextGroup"
                    class="pagination-button"
                >
                    &gt;
                </button>
                <button
                    @click="goToLastPage"
                    :disabled="currentPage === totalPages - 1"
                    class="pagination-button"
                >
                    &gt;&gt;
                </button>
            </div>
        </div>

        <div class="notice">
            <div class="notice-more">
                <p>공지사항</p>
                <a class="move" @click="goNoticeList()">더보기 ></a>
            </div>

            <ul class="notice-list" v-for="notice in notices" :key="notice.id">
                <li>
                    <div class="notice-wrap">
                        <img src="@/assets/yellow-dot.svg" alt="" class="dot">
                        <div class="go-notice" @click="goNoticeDetail(notice.id)">
                            <p class="notice-title">
                                {{ notice.title }}</p>
                            <p class="notice-date">{{ formatDate(notice.createdAt) }}</p>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>
.wrap {
    display: flex;
    justify-content: space-between;
    width: 100%;
}

.question {
    width: 65%;
}

.notice {
    width: 30%;
}

.move {
    cursor: pointer;
}

.question-wrap {
    cursor: pointer;
}

.question-write {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 50px;
    margin-bottom: 15px;
    padding: 0 15px;
    background-color: #333A73;
    border: none;
    border-radius: 15px;
    font-family: 'Nexon Bold', sans-serif;
    color: #FFFFFF;
    font-size: 20px;
}

.question-list {
    list-style: none;
    margin-bottom: 15px;
}

.question-writer {
    display: flex;
    justify-content: space-between;
    padding: 0 15px 10px 15px;
}

.question-writer span {
    font-family: 'Nexon Light', sans-serif;
    font-size: 15px;
}

.question-title {
    display: flex;
    justify-content: space-between;
    padding: 0 15px 15px 15px;
}

.question-title span {
    font-family: 'Nexon Medium', sans-serif;
    font-size: 20px;
}

.notice-more {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 50px;
    margin-bottom: 15px;
    padding: 0 15px;
    background-color: #FBA834;
    border: none;
    border-radius: 15px;
    font-family: 'Nexon Bold', sans-serif;
    color: #000000;
    font-size: 20px;
}

.notice-list {
    list-style: none;
    margin-bottom: 15px;
}

.notice-wrap {
    display: flex;
}

.dot {
    width: 10px;
    height: 10px;
    margin: 10px 10px 0 0;
}

.notice-title {
    cursor: pointer;
    margin: 0 0 10px 0;
    font-family: 'Nexon Medium', sans-serif;
    font-size: 20px;
}

.notice-date {
    font-family: 'Nexon Light', sans-serif;
    font-size: 15px;
}

.go-notice {
    cursor: pointer;
    width: 100%;
}

.pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    gap: 5px;
}

.pagination-button {
    padding: 5px 10px;
    border: 1px solid #333A73;
    background-color: #FFFFFF;
    color: #333A73;
    cursor: pointer;
    font-family: 'Nexon Medium', sans-serif;
    min-width: 35px;
}

.pagination-button:disabled {
    border-color: #ccc;
    color: #ccc;
    cursor: not-allowed;
}

.pagination-button.active {
    background-color: #333A73;
    color: #FFFFFF;
}

.pagination-button:hover:not(:disabled) {
    background-color: #f0f0f0;
}

.question-title1 {
    flex: 5;
    overflow-x: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}

.question-title2 {
    flex: 3;
    text-align: end;
}
</style>

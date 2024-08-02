import axios from "axios";
import {setInterceptors} from "@/api/interceptors";

// const hasAuthHeader = !!sessionStorage.getItem('authHeader');
// const authHeader = sessionStorage.getItem('authHeader');
//
// const instance = axios.create({
//     headers: {
//         'Authorization': hasAuthHeader ? authHeader : ''
//     }
// })
const createAuthInstance = function () {
    const instance = axios.create({
    })
    return setInterceptors(instance);
}
const authInstance = createAuthInstance();

const createUserAPI = function (request) {
    return authInstance.post(`/api/users/join`, request);
}

const getQuestionsAPI = function (request) {
    return authInstance.get(`/api/questions?title=${request.title}&size=${request.size}&page=${request.page}`);
}

const getQuestionAPI = function (id) {
    return authInstance.get(`/api/questions/${id}`);
}

const createQuestionAPI = function (request) {
    return authInstance.post(`/api/questions`, request)
}

const getNoticeAPI = function (id) {
    return authInstance.get(`/api/notices/${id}`)
}

const createNoticeAPI = function (request) {
    return authInstance.post(`/api/notices`, request);
}

const editNoticeAPI = function (id, request) {
    return authInstance.put(`/api/notices/${id}`, request)
}

const deleteNoticeAPI = function (id) {
    return authInstance.delete(`/api/notices/${id}`);
};

export {createUserAPI, getQuestionsAPI, getQuestionAPI, createQuestionAPI, getNoticeAPI, editNoticeAPI, deleteNoticeAPI, createNoticeAPI}

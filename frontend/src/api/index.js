import axios from "axios";

const hasAuthHeader = !!sessionStorage.getItem('authHeader');
const authHeader = sessionStorage.getItem('authHeader');

const instance = axios.create({
    headers: {
        'Authorization': hasAuthHeader ? authHeader : ''
    }
})

const createUserAPI = function (request) {
    return instance.post(`/api/users/join`, request);
}

const getQuestionsAPI = function (request) {
    return instance.get(`/api/questions?title=${request.title}&size=${request.size}&page=${request.page}`);
}

const getQuestionAPI = function (id) {
    return instance.get(`/api/questions/${id}`);
}

const createQuestionAPI = function (request) {
    return instance.post(`/api/questions`, request)
}

const getNoticeAPI = function (id) {
    return instance.get(`/api/notices/${id}`)
}

const editNoticeAPI = function (id, request) {
    return instance.put(`/api/notices/${id}`, request)
}

const deleteNoticeAPI = function (id) {
    return instance.delete(`/api/notices/${id}`);
};

export {createUserAPI, getQuestionsAPI, getQuestionAPI, createQuestionAPI, getNoticeAPI, editNoticeAPI, deleteNoticeAPI}

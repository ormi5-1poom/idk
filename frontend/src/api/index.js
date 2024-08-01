import axios from "axios";

const createUserAPI = function (request) {
    return axios.post(`/api/users/join`, request);
}

const getQuestionsAPI = function (request) {
    return axios.get(`/api/questions?title=${request.title}&size=${request.size}&page=${request.page}`);
}

const getQuestionAPI = function (id) {
    return axios.get(`/api/questions/${id}`);
}

export {createUserAPI, getQuestionsAPI, getQuestionAPI}

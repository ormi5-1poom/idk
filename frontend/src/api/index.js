import axios from "axios";

const createUserAPI = function (request) {
    return axios.post(`/api/users/join`, request);
}

const getQuestionsAPI = function (request) {
    return axios.get(`/api/questions?title=${request.title}&size=${request.size}&page=${request.page}`)
}

export {createUserAPI, getQuestionsAPI}

import {createStore} from "vuex";
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    state: {

    },
    getters: {

    },
    mutations: {

    },
    actions: {

    },
    plugins: [ createPersistedState({
        paths: []
    })

    ]
})

export default store;

import { createApp } from 'vue'
import App from './App.vue'
import routes from './router'
import store from "@/store";
import axios from "axios";

const app = createApp(App);
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$store = store;
app.use(routes).use(store).mount('#app')

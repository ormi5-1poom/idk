import {createRouter, createWebHistory} from "vue-router";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import HomeView from "@/views/HomeView.vue";
import QuestionView from "@/views/QuestionView.vue";
import NoticeView from "@/views/NoticeView.vue";
import MyView from "@/views/MyView.vue";

const routes = [
    {
        path: "/login",
        name: "Login",
        component: LoginView,
    },
    {
        path: "/signup",
        name: "Signup",
        component: SignupView
    },
    {
        path:  "/",
        name: "Home",
        component: HomeView
    },
    {
        path: "/question/:questionId",
        name: "Question",
        component: QuestionView
    },
    {
        path: "/notice/:noticeId",
        name: "Notice",
        component: NoticeView
    },
    {
        path: "/my",
        name: "My",
        component: MyView
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// router.beforeEach((to, from, next) => {
//     const isAuthenticated = !!sessionStorage.getItem('authHeader');
//
//     if (!isAuthenticated) {
//         next('/login');
//     } else {
//         next();
//     }
// })


export default router;

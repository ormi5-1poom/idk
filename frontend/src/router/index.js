import {createRouter, createWebHistory} from "vue-router";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import HomeView from "@/views/HomeView.vue";
import QuestionView from "@/views/QuestionView.vue";
import NoticeView from "@/views/NoticeView.vue";
import MyView from "@/views/MyView.vue";
import QuestionWriteView from "@/views/QuestionWriteView.vue";
import AdminView from "@/views/AdminView.vue";
import NoticeListView from "@/views/NoticeListView.vue";
import NoticeWriteView from "@/views/NoticeWriteView.vue";
import NoticeEditView from "@/views/NoticeEditView.vue";

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
        path: "/question/write",
        name: "QuestionWrite",
        component: QuestionWriteView
    },
    {
        path: "/notice/:noticeId",
        name: "Notice",
        component: NoticeView
    },
    {
        path: "/notice",
        name: "NoticeList",
        component: NoticeListView
    },
    {
        path: "/notice/write",
        name: "NoticeWrite",
        component: NoticeWriteView
    },
    {
        path: "/notice/:noticeId/edit",
        name: "NoticeEdit",
        component: NoticeEditView
    },
    {
        path: "/my",
        name: "My",
        component: MyView
    },
    {
        path: "/admin",
        name: "Admin",
        component: AdminView
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// router.beforeEach(async (to, from, next) => {
//     try {
//         if (to.path === '/login' || to.path === '/' || to.path === '/signup') {
//             next();
//         } else if (to.path === '/admin') {
//             const response = await axios.post('/api/users/login', null);
//             if (response.data.roles[0] === "ADMIN") {
//                 console.log(response.data.roles[0]);
//                 next();
//             } else {
//                 next('/login');
//             }
//         } else {
//             next('/');
//         }
//     } catch (error) {
//         console.log(error);
//     }
//
// })


export default router;

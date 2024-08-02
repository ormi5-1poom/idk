import {createRouter, createWebHistory} from "vue-router";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import HomeView from "@/views/HomeView.vue";
import QuestionView from "@/views/QuestionView.vue";
import NoticeView from "@/views/NoticeView.vue";
import MyView from "@/views/MyView.vue";
import WriteView from "@/views/WriteView.vue";
import AdminView from "@/views/AdminView.vue";
import NoticeListView from "@/views/NoticeListView.vue";

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
    },
    {
        path: "/write",
        name: "Write",
        component: WriteView
    },
    {
        path: "/admin",
        name: "Admin",
        component: AdminView
    },
    {
        path: "/notice",
        name: "NoticeList",
        component: NoticeListView
    }
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

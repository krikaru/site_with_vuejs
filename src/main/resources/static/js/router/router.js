import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Auth from "pages/Auth.vue";
import Profile from "pages/Profile.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessagesList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '*', component: MessagesList },//любой запрос перенаправляется в MessagesList, но не те, что идут выше этой строки
]

export default new VueRouter({
    mode: 'history',
    routes // сокращённая запись для `routes: routes`
})
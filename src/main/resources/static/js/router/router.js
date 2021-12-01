import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Auth from "pages/Auth.vue";
import Profile from "pages/Profile.vue";
import Subscriptions from 'pages/Subscriptions.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessagesList },
    { path: '/auth', component: Auth },
    { path: '/user/:id?', component: Profile }, ///user/:id? - динамический. ? - означает необязательный
    { path: '/subscriptions/:id', component: Subscriptions },
    { path: '*', component: MessagesList },//любой запрос перенаправляется в MessagesList, но не те, что идут выше этой строки
]

export default new VueRouter({
    mode: 'history',
    routes // сокращённая запись для `routes: routes`
})
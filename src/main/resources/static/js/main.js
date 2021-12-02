import Vue from 'vue'
import Vuetify from "vuetify";
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from "store/store";
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css' //подключаем стили

if(profile) {
    connect()  //открытие сокета
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store, //store: store
    router,
    vuetify: new Vuetify(),
    //это значит что нужно в элемент el: '#app' поместить отрендеренный компонент App
    render: a => a(App)
})



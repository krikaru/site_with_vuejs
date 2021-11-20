import Vue from 'vue'
import Vuetify from "vuetify";
import '@babel/polyfill'
import 'api/resource'
import App from 'pages/App.vue'
import store from "store/store";
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css' //подключаем стили

if(frontendData.profile) {
    connect()  //открытие сокета
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store, //store: store
    vuetify: new Vuetify(),
    //это значит что нужно в элемент el: '#app' поместить отрендеренный компонент App
    render: a => a(App)
})



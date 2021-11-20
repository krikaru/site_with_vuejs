import Vue from 'vue'
import 'api/resource'
import App from 'pages/App.vue'
import { connect } from './util/ws'
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css' //подключаем стили

if(frontendData.profile) {
    connect()  //открытие сокета
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    //это значит что нужно в элемент el: '#app' поместить отрендеренный компонент App
    render: a => a(App)
})



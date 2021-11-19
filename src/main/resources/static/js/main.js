import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import { connect } from './util/ws'
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css' //подключаем стили

if(frontendData.profile) {
    connect()  //открытие сокета
}



//регистрируем vue-resource, дальше во всем приложении мы можем его использовать как поле экземпляра vue
Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    //это значит что нужно в элемент el: '#app' поместить отрендеренный компонент App
    render: a => a(App)
})



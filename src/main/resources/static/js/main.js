import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
//регистрируем vue-resource, дальше во всем приложении мы можем его использовать как поле экземпляра vue
Vue.use(VueResource)

new Vue({
    el: '#app',
    //это значит что нужно в элемент el: '#app' поместить отрендеренный компонент App
    render: a => a(App)
})



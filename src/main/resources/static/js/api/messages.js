import Vue from 'vue'

//Это ресурс!!!
const messages = Vue.resource('/message{/id}')

export default {
    add: message => messages.save({}, message),
    update: message => messages.update({id: message.id}, message),
    remove: id => messages.remove({id}),
    page: page => Vue.http.get('/message', {params: {page: page}}) // ресурс будет таким: '/message?page=1'. Поэтому не используем vueresource

}
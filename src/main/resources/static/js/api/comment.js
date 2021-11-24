import Vue from 'vue'

//Это ресурс!!!
const comments = Vue.resource('/comment{/id}')

export default {
    add: comment => comments.save({}, comment)
}
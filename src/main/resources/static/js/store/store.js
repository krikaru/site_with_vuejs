import Vue from 'vue'
import Vuex from 'vuex'
import messagesApi from 'api/messages'
import commentApi from 'api/comment'
import comment from "api/comment";

Vue.use(Vuex)
//state и getters в компонентах должны быть в разделе computed!!!!
//action и mutations в компонентах дожны быть в разделе methods!!!!
export default new Vuex.Store({
    state: {
        messages: messages,
        profile: frontendData.profile
    },
    getters: {
        //если меседжей нет, то возвращаем пустой массив
        sortedMessages: state => (state.messages || []).sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addMessageMutation(state, message) {
            //Целиком заменить старый объект новым.
            state.messages = [
                ...state.messages, //старый объект
                message            //добавляем к старому новый
            ]
        },
        updateMessageMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex + 1)
            ]
        },
        removeMessageMutation(state, message) {
            const deletionIndex = state.messages.findIndex(item => item.id === message.id)

            if (deletionIndex > -1) {
                state.messages = [
                    ...state.messages.slice(0, deletionIndex),
                    ...state.messages.slice(deletionIndex + 1)
                ]
            }
        },
        addCommentMutation(state, comment) {
            const updateIndex = state.messages.findIndex(item => item.id === comment.message.id)
            const message = state.messages[updateIndex]
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                {
                    ...message,
                    comments: [
                        ...message.comments,
                        comment
                    ]
                },
                ...state.messages.slice(updateIndex + 1)
            ]
        },
    },
    actions: {
        async addMessageAction({commit, state}, message) {
            const result = await messagesApi.add(message)
            const data = await result.json()
            const index = state.messages.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateMessageMutation', data); //вызываем мутацию
            } else {
                commit('addMessageMutation', data);
            }
        },

// без acync!!!
// messagesApi.add(message).then(result =>
//     result.json().then(data => {
//         const index = this.messages.findIndex(item => item.id === data.id)
//
//         if (index > -1) {
//             this.messages.splice(index, 1, data);
//         } else {
//             this.messages.push(data);
//         }
//
//     })
// )
        async updateMessageAction({commit}, message) {
            const result = await messagesApi.update(message)
            const data = await result.json()

            commit('updateMessageMutation', data)
        },
        async removeMessageAction({commit}, message) {
            const result = await messagesApi.remove(message.id)
            if (result.ok) {
                commit('removeMessageMutation', message)
            }

        },
        async addCommentAction({commit, state}, comment) {
            const response = await commentApi.add(comment)
            const data = response.json()
            commit('addCommentMutation', comment)
        }
    },

})
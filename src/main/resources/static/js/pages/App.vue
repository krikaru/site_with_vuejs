<template>
<!--    <div class="main-app">-->
<!--  v-app главный тег, чтобы стили нормально отображались  -->
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Chibis</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
<!--                иконки из https://fonts.google.com/icons?selected=Material+Icons-->
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <v-container v-if="!profile"> Необходимо авторизоваться через <a href="/login">Google</a></v-container>
            <v-container v-if="profile">
                <messages-list :messages="messages" />
            </v-container>
        </v-main>


    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
    import { addHandler } from "util/ws";
    import { getIndex } from "util/collection";

    export default {
        //регистрируем компоненты
        components: {
          MessagesList  //преобразование в messages-list происходит автоматически
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id)
                if (index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style scoped>
/*.main-app {*/
/*    color: green;*/
/*}*/
</style>
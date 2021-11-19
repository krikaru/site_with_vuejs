<template>
    <div>
        <div v-if="!profile"> Необходимо авторизоваться через <a href="/login">Google</a></div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <messages-list :messages="messages" />
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
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
        }
    }
</script>

<style scoped>

</style>
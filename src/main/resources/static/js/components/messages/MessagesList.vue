<template>
    <div>
        <message-form :messages="messages" :editMessage="message" />
        <message-row v-for="message in messages" :key="message.id"
                     :message="message"
                     :editMethod="edit"
                     :deleteMethod="del"
                     :messages="messages"
        />
    </div>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue';
    import MessageForm from 'components/messages/MessageForm.vue';

    export default {
        props: ['messages'],
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            edit(message) {
                this.message = message
            },
            del(message) {
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.findIndex((element) => element.id === message.id), 1);
                    }
                })
            }
        }
    }
</script>

<style>
</style>
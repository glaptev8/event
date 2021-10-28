<template>
    <div>
        <input type="text" placeholder="Введите сообщение" v-model="text">
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    import { sendMessage } from "../../util/ws";

    export default {
        props: ['messages', 'editMessage'],
        watch: {
            editMessage(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
        data() {
            return {
                text: '',
                id: ''
            }
        },
        methods: {
            save() {
                sendMessage({id: this.id, text: this.text})
                this.id = ''
                this.text = ''
                /*var message;
                if (this.id) {
                    message = {text: this.text, id: this.id};
                    var index = this.messages.findIndex((element) => element.id === this.id);
                    console.log(index);
                    this.$resource('/message{/id}').update({id: message.id}, message).then(result => result.json().then(data => {
                        console.log(data);
                        this.messages.splice(index, 1, data);
                    }));
                    this.id = '';
                    this.text = '';
                } else {
                    message = {text: this.text};
                    this.$resource('/message{/id}').save({}, message).then(result => result.json().then(data => {
                        this.messages.push(data);
                    }));
                    this.text = '';
                } */
            }
        }
    }
</script>

<style>

</style>
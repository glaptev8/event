import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import {connect} from "./util/ws";
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'vuetify/dist/vuetify.min.css'
import VueRouter from "vue-router";
import router from 'router/router'
import store from 'store/store'

connect()

Vue.use(VueResource)
Vue.use(Vuetify)
Vue.use(VueRouter)

new Vue({
    el: "#app",
    vuetify : new Vuetify(),
    router,
    store,
    render: a => a(App)
})

/*
var messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages', 'editMessage'],
    watch: {
        editMessage: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    data: function() {
            return {
                text: '',
                id: ''
            }
        },
    template:
        '<div>' +
            '<input type="text" placeholder="Введите сообщение" v-model="text">' +
            '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var message;
            if (this.id) {
                message = {text: this.text, id: this.id};
                var index = this.messages.findIndex((element) => element.id === this.id);
                console.log(index);
                messageApi.update({id: message.id}, message).then(result => result.json().then(data => {
                    console.log(data);
                    this.messages.splice(index, 1, data);
                }));
                this.id = '';
                this.text = '';
            } else {
                message = {text: this.text};
                messageApi.save({}, message).then(result => result.json().then(data => {
                    this.messages.push(data);
                }));
                this.text = '';
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template:
        '<div> ' +
            '{{message.text}}' +
            '<span style="position: absolute; right: 0">' +
                '<input type="button" value="Edit" @click="edit" />' +
                '<input type="button" value="X" @click="del" />' +
            '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.message);
        },
        del: function () {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.findIndex((element) => element.id === this.message.id), 1);
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div>' +
            '<message-form :messages="messages" :editMessage="message" />' +
            '<message-row v-for="message in messages" :message="message" :editMethod="edit" :messages="messages"/>' +
        '</div>',
    created: function () {
        messageApi.get().then(
            result => result.json().then(
                data => data.forEach(
                        message => this.messages.push(message
                    )
                )
            )
        )
    },
    methods: {
        edit: function(message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: []
    }
});*/

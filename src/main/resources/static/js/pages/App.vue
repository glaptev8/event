<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Event</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn @click="logOut">
                <a href="/signIn">logOut</a>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    // import MessagesList from 'components/messages/MessagesList.vue'
    // import {addHandler} from "../util/ws";
    import {mapActions} from 'vuex'

    export default {
        components: {
            // MessagesList
        },
        methods: {
            ...mapActions(['setDictionaries']),
            logOut() {
                this.$resource('/logout').save()
            }
        },
        // },
        // data() {
        //     return {
        //         messages: []
        //     }
        // },
        created() {

            // this.$resource('/message{/id}').get().then(
            //     result => result.json().then(
            //         data => data.forEach(
            //             message => this.messages.push(message)
            //         )
            //     )
            // ),
            //     addHandler(data => {
            //         console.log("qq");
            //         let index = this.messages.findIndex((element) => element.id === data.id);
            //         if (index > -1) {
            //             this.messages.splice(index, 1, data)
            //         } else {
            //             this.messages.push(data)
            //         }
            //     })
            this.$resource('/v1/api/get/dictionaries').get().then(result => {
                if (result.ok) {
                    result.json().then(json => {
                        this.setDictionaries(json)
                    })
                }
            })
        }
    }
</script>

<!--<style>-->
<!--</style>-->
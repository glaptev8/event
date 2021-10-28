<template>
    <form @submit.prevent="submit">
        <v-text-field
                v-model="user.username"
                required
                label="username"
        ></v-text-field>
        <v-text-field
                v-model="user.phone"
                required
                label="phone"
        ></v-text-field>
        <v-text-field
                v-model="user.password"
                required
                label="password"
                type="password"
        ></v-text-field>
        <v-file-input
                v-model="user.avatars"
                truncate-length="50"
        ></v-file-input>
        <v-btn
                class="mr-4"
                type="submit"
                :disabled="invalid"
        >submit</v-btn>
    </form>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "SignUp",
        computed: mapState(['authUser']),
        data() {
            return {
                user: {
                    avatars: null,
                    username: '',
                    phone: '',
                    password: '',
                    },
            }
        },
        watch: {
          user: function (newVal) {
              console.log(newVal)
          }
        },
        methods: {
            submit () {
                this.$resource("/api/v1/signUp").save(this.user).then(result => {
                    if (result.ok) {
                        console.log(result)
                        this.authUser.username = this.user.username
                        this.authUser.phone = this.user.phone
                        this.$router.push("/profile")
                    }
                })
            },
        },
    }
</script>

<style scoped>

</style>
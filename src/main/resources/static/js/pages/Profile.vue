<template>
    <v-main>
        <v-row>
            <v-col sm="3">
                <v-card>
                    <form @submit.prevent="submit" class="form">
                        <v-text-field
                                required
                                v-model="authUserCopy.username"
                                label="username"
                        ></v-text-field>
                        <v-text-field
                                v-model="authUserCopy.phone"
                                required
                                label="phone"
                        ></v-text-field>
                        <input @change="handleMessage" type="file" accept="image/*">
                        <v-btn
                                class="mr-4"
                                type="submit"
                        >submit</v-btn>
                    </form>
                    <event-create-form></event-create-form>
                </v-card>
            </v-col>
            <v-col sm="9">
                <v-card>
                    <v-carousel v-model="currentValue">
                        <v-carousel-item
                                v-for="(item,i) in this.avatars"
                                :key="i"
                                :src="item.avatar"
                                reverse-transition="fade-transition"
                                transition="fade-transition"
                        ></v-carousel-item>
                    </v-carousel>
                    <v-btn
                            class="mr-4"
                            @click="deleteImage(currentValue)"
                    >delete</v-btn>
                </v-card>
            </v-col>
        </v-row>
    </v-main>
</template>

<script>
    import {mapActions} from 'vuex'
    import EventCreateForm from "components/event/EventCreateForm.vue";

    export default {
        name: "Profile",
        components: {
            EventCreateForm,
        },
        computed: {
            avatars: {
                get() {
                    return this.$store.getters["authUser"].avatars
                },
                set(newValue) {
                    return this.$store.dispatch("setAvatars", newValue);
                }
            },
            authUser: {
                get() {
                    return this.$store.getters["authUser"]
                },
                set(newValue) {
                    return this.$store.dispatch("setAuthUser", newValue);
                }
            },
        },
        data() {
          return {
              authUserCopy: {
                  username: '',
                  phone: '',
                  avatars: [
                      {avatar: '', id: ''},
                  ]
              },
              currentValue: null
          }
        },
        methods: {
            ...mapActions(['addImage', 'setAuthUser', 'addImageToObject', 'deleteImageById']),
            submit () {
                this.$resource("/api/v1/profile").save(this.authUserCopy).then(result => {
                    if (result.ok) {
                        Array.prototype.push.apply(this.authUserCopy.avatars, this.avatars);
                        this.setAuthUser(JSON.parse(JSON.stringify(this.authUserCopy)))
                        this.authUserCopy.avatars = []
                    }
                })
            },
            handleMessage(image) {
                const selectImage = image.target.files[0];
                const reader = new FileReader()
                let q = this;
                reader.onload = function(event) {
                    q.authUserCopy.avatars.push({avatar: reader.result});

                };
                reader.readAsDataURL(selectImage)

            },
            deleteImage(index) {
                console.log(index)
                console.log(this.avatars[index].id)
                this.$resource("/api/v1/profile/image/delete").save(this.avatars[index].id).then(result => {
                  if (result.ok) {
                    this.deleteImageById(this.avatars[index].id)
                  }
                })
            }
        },
        beforeMount() {
            this.authUserCopy = JSON.parse(JSON.stringify(this.authUser))
            this.authUserCopy.avatars = []
        }
    }
</script>

<style>
.form {
    margin-bottom: 30px;
}
</style>
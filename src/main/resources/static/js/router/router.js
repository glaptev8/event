import Vue from 'vue'
import VueRouter from 'vue-router'
import Profile from "pages/Profile.vue";
import SignUp from "pages/SignUp.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/profile', component: Profile },
    { path: '/signUp', component: SignUp },
]

export default new VueRouter({
    mode: 'history',
    routes // сокращённая запись для `routes: routes`
})
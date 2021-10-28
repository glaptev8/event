import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        authUser: frontendData.authUser,
        dictionary: ''
    },
    mutations: {
        SET_USER_AVATARS(state, avatars) {
            state.authUser.avatars = avatars
        },
        SET_USER_NAME(state, username) {
            state.authUser.username = username
        },
        SET_USER_PHONE(state, phone) {
            state.authUser.phone = phone
        },
        SET_AUTH_USER(state, authUser) {
            state.authUser = authUser
        },
        ADD_IMAGES(state, avatar) {
            state.authUser.avatars = [
                ...state.authUser.avatars,
                avatar
            ]
        },
        DELETE_IMAGE(state, index) {
            state.authUser.avatars.splice(index, 1)
        },
        SET_DICTIONARIES(state, dictionaries) {
            state.dictionary = dictionaries
        }
    },
    actions: {
        async setAvatars({ commit }, newValue) {
            commit("SET_USER_AVATARS", newValue)
        },
        async setUserName({ commit }, newValue) {
            commit("SET_USER_NAME", newValue)
        },
        async setPhone({ commit }, newValue) {
            commit("SET_USER_PHONE", newValue)
        },
        async setAuthUser({ commit }, newValue) {
            commit("SET_AUTH_USER", newValue)
        },
        async addImage({ commit }, image) {
            commit("ADD_IMAGES", image)
        },
        async deleteImageById({ commit }, id) {
            for (let i = 0; i < this.state.authUser.avatars.length; i++) {
                if (this.state.authUser.avatars[i].id === id) {
                    commit("DELETE_IMAGE", i)
                    break
                }
            }
        },
        async setDictionaries({ commit }, dictionaries) {
            commit("SET_DICTIONARIES", dictionaries)
        }
    },
    getters: {
        authUser: state => {
            return state.authUser;
        }
    },
})
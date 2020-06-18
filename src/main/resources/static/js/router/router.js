import Vue from 'vue'
import VueRouter from 'vue-router'
import TariffsList from 'pages/TariffList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: TariffsList},
    {path: '/auth', component: Auth},
    {path: '/profile', component: Profile},
    {path: '*', component: TariffsList},
]

export default new VueRouter({
    mode: 'history',
    routes
})
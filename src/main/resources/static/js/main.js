import Vue from 'vue'
import 'api/resource'
import App from 'pages/App.vue'
import '@babel/polyfill'
import store from 'store/store'
import {connect} from './util/ws'
import vuetify from './plugin/vuetify'
import 'vuetify/dist/vuetify.min.css'
import router from "router/router";


if (frontendData.profile) {
    connect()
}


new Vue({
    el: '#app',
    vuetify,
    store,
    router,
    render: a => a(App)

})

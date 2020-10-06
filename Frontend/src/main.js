import Vuesax from 'vuesax';
import 'vuesax/dist/vuesax.css' //Vuesax styles
Vue.use(Vuesax)
import './assets/scss/index.scss';
import axios from './util/axios';

import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';


Vue.config.productionTip = false

Vue.prototype.$http = axios
Vue.prototype.$url = process.env.VUE_APP_PROD_URL

new Vue({
  router,
  store,
  vuetify,
  render: function (h) { return h(App) }
}).$mount('#app')

export { axios }
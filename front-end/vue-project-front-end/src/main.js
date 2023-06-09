import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router.js'
import '/node_modules/bootstrap/dist/css/bootstrap.css'
import * as bootstrap from 'bootstrap'

createApp(App).use(router).mount('#app')
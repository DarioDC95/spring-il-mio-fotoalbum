import { createRouter, createWebHistory } from 'vue-router'

import HomePage from './views/HomePage.vue'
import AddMessage from './views/AddMessage.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
      {
        name: 'homepage',
        path: '/',
        component: HomePage
      },
      {
        name: 'AddMessage',
        path: '/create-message',
        component: AddMessage
      }
    ]
})  

export { router }
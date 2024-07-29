import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import VueCountdown from '@chenfengyuan/vue-countdown';

createApp(App).use(router)
  .use(ElementPlus, { size: 'small', zIndex: 3000 })
  .component(VueCountdown.name, VueCountdown)
  .mount('#app')


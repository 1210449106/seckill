import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/goodsList',
    name: 'goodsList',
    component: () => import('@/views/GoodsList.vue')
  },
  {
    path: '/goodsDetail/:goodsId',
    name: 'goodsDetail',
    component: () => import('@/views/GoodsDetail.vue')
  },
  {
    path: '/:catchAll(.*)',
    component: () => import('@/views/GoodsList.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router

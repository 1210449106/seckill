// 引入axios
import request from '@/until/axios'

// 根据id获取商品详情
export function getGoodsDetail (id) {
  return request.get('/goods/' + id)
}

// 获取商品列表
export function getGoodsList () {
  return request.get('/goods')
}

// 引入axios
import request from '@/until/axios'

// 根据id获取订单详情
export async function getOrderDetail (id) {
  return await request.get('/order/' + id)
}

// 获取订单支付结果
export async function getOrderResult (goodsId, userId) {
  return await request.get('/order/result/' + goodsId + '/' + userId)
}

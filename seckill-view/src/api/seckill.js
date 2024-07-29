// 引入axios
import request from '@/until/axios'

export async function saveSeckill(randomNum, seckillVo) {
  return await request.post('/seckill/' + randomNum, {
    goodsId: seckillVo.goodsId,
    userId:seckillVo.userId,
    goodsCount: seckillVo.goodsCount
  })
}

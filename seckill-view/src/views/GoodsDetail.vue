<script>
import { getGoodsDetail } from '@/api/goods'
import { saveSeckill } from '@/api/seckill'
import { getOrderResult } from '@/api/order'

export default {
  data () {
    return {
      btnDisable: false,
      userId: null,
      goodsCount: 1,
      goods: {
        goodsId: "id",
        title: "title",
        introduce: "introduce",
        price: 1234.00,
        count: 0,
        randomNum: null,
        image: null,
        startTime: "startTime",
        endTime: "endTime",
        status: 0,
        gapTime: null
      },
      prompt: null
    }
  },
  methods: {
    async doSeckill () {
      // 下单成功后
      // 禁用按钮,显示提示性文字
      this.btnDisable = true
      this.prompt = "正在秒杀中，请稍后。。。。"
      const seckillVo = {
        userId: this.userId,
        goodsId: this.goods.goodsId,
        goodsCount: this.goodsCount
      }
      // 发送请求
      const data = await saveSeckill(this.goods.randomNum, seckillVo)
      if (data.code === 200) {
        // 开始轮询下单结果(3000ms/次)
        this.getOrderRsId = setInterval(this.intervalRs, 3000)
        this.prompt = data.message
      } else if (data.code === 501) {
        this.prompt = data.message
      } else if (data.code === 502) {
        this.prompt = data.message
      } else if (data.code === 503) {
        this.prompt = data.message
      } else {
        this.prompt = "网络异常,请重新下单"
      }
    },
    //下单结果查询
    async intervalRs () {
      const data = await getOrderResult(this.goods.goodsId, this.userId)
      if (data.code !== 200) {
        this.prompt = data.message
        clearInterval(this.getOrderRsId)
      }
    }
  },
  computed: {
    seckillStatus () {
      if (this.goods.status === 0) {
        return "秒杀未开始"
      }
      if (this.goods.status === 1) {
        return "秒杀正在进行中"
      }
      if (this.goods.status === 2) {
        return "秒杀已结束"
      }
    }
  },
  async mounted() {
    document.title = "商品详情"
    const goods = await getGoodsDetail(this.$route.params.goodsId)
    if (goods.code === 200) {
      this.goods = goods.data
      document.title = "商品详情-" + this.goods.title
    } else {
      alert("网络异常")
    }
  },
  unmounted() {
    // 关闭定时任务轮询
    clearInterval(this.getOrderRsId)
  }
}
</script>

<template>
  <div>
    <el-row>
      <el-col :span="4" :offset="3">
        <h1>商品详情</h1>
      </el-col>
      <el-col :span="9" :offset="3">
      </el-col>
    </el-row>
    <el-row >
      <el-col :span="18" :offset="3">
        <el-row :gutter="22">
          <el-col :span="6">
            <img :src="goods.image" style="width: 100%;height: 100%">
          </el-col>
          <el-col :span="18">
            <h4>{{ goods.title }}</h4>
            <h4>{{ goods.introduce }}</h4>
            <h3 style="color: red"> {{ goods.price }} </h3>
            <p style="color: darkgray">剩余{{ goods.count }}件</p>
            <br/><br/>
            <h3 v-if="!btnDisable" style="color: tomato">{{ seckillStatus }}</h3>
            <vue-countdown v-if="goods.status === 0" :time="goods.gapTime" v-slot="{ days, hours, minutes, seconds }">
              距离秒杀开始还有：{{ days }} 天  {{ hours }} 小时  {{ minutes }} 分钟  {{ seconds }} 秒.
            </vue-countdown>
<!--            提示性文字-->
            <h3 v-show="btnDisable" style="color: orangered">{{ this.prompt }}</h3>
            <template v-if="goods.status === 1">
              <el-input v-show="!btnDisable" v-model="userId" style="width: 240px" placeholder="请输入您的id" />
              <br/>
              <el-button :size="'large'" :disabled="btnDisable" v-show="!btnDisable" type="danger" @click="doSeckill">立即秒杀</el-button>
            </template>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="less">

</style>

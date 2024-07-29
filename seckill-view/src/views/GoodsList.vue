<script>
import GoodsCard from '@/components/GoodsCard.vue'
import { getGoodsList } from '@/api/goods'

export default {
  data () {
    return {
      goodsList: []
    }
  },
  async mounted() {
    document.title = "商品详情"
    const data = await getGoodsList()
    if (data.code === 200) {
      this.goodsList = data.data
      console.log(data.data)
    } else {
      alert("网络异常")
    }
  },
  components: {
    GoodsCard
  }
}
</script>

<template>
  <div>
    <el-row>
      <el-col :span="4" :offset="3">
        <h1>商品列表</h1>
      </el-col>
      <el-col :span="9" :offset="3">
      </el-col>
    </el-row>
    <el-row >
      <el-col :span="18" :offset="3">
        <el-row :gutter="22">
          <el-col
            :span="6"
            v-for="item in goodsList">
<!--            商品卡片-->
            <goods-card
              :goodsId="item.goodsId"
              :title="item.title"
              :introduce="item.introduce"
              :price="item.price"
              :image="item.image"
              :startTime="item.startTime"
              :endTime="item.endTime"
              :status="item.status"
              :gapTime="item.gapTime"></goods-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="less">
</style>

<script setup>
const props = defineProps({
  goodsId: Number,
  title: String,
  introduce: String,
  price: Number,
  image: String,
  startTime: String,
  endTime: String,
  status: Number,
  gapTime: Number
})
function getStatus() {
  if (props.status === 0) {
    let seconds = props.gapTime / 1000;
    // 计算天数
    let days = Math.floor(seconds / (24 * 3600));
    // 计算剩余的小时数
    let hours = Math.floor((seconds % (24 * 3600)) / 3600);
    // 计算剩余的分钟数
    let minutes = Math.floor((seconds % 3600) / 60);
    // 计算剩余的秒数
    let secondsLeft = Math.floor(seconds % 60);
    // 格式化输出
    return `距离秒杀开始还有${days}天 ${hours}小时 ${minutes}分钟 ${secondsLeft}秒`
  }
  if (props.status === 1) {
    return "秒杀中"
  }
  if (props.status === 2) {
    return "秒杀已结束"
  }
}
</script>

<template>
  <router-link :to="'/goodsDetail/' + props.goodsId">
    <el-row>
      <el-col>
<!--        图片-->
        <img :src="props.image" style="width: 100%;height: 100%">
      </el-col>
    </el-row>
    <el-row>
      <el-col>
<!--        文字-->
        <h3>{{ props.title }}</h3>
        <el-text class="w-150px mb-2" truncated>
          {{ props.introduce }}
        </el-text>
        <br/>
        <h4 style="color: red">
          秒杀惊爆价：{{ props.price }}
        </h4>
        <h5 style="color: orange">
          {{ getStatus() }}
        </h5>
      </el-col>
    </el-row>

  </router-link>
</template>

<style scoped lang="less">

</style>

<script setup>
import { ref } from 'vue'

const input = ref('')
</script>

<template>
  <div>
    <el-row :gutter="22">
      <el-col :span="4" :offset="3">
        <a href="/">
          <img style="width: 100%;height: 100%" src="https://img.alicdn.com/imgextra/i3/O1CN01lwjvx91rJQu7v1PJn_!!6000000005610-1-tps-480-144.gif">
        </a>
      </el-col>
      <el-col :span="12" style="margin-top: 20px">
        <el-input v-model="input" style="width: 100%;height: 45px" placeholder="请输入商品名"/>
      </el-col>
      <el-col :span="2" style="margin-top: 20px;width: 80px">
        <el-button size="large" type="warning">搜索</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="18" :offset="3" style="height: 20px;width: 100%;background-color: #ff6200"></el-col>
    </el-row>
  </div>
</template>

<style scoped lang="less">

</style>

<script setup>
import WebHeader from '@/components/WebHeader.vue'

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <web-header></web-header>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<style lang="less">
a {
  text-decoration: none;
}
</style>

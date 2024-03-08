<template>
  <header>
    <h1>Device Statuses</h1>
  </header>
  <main>
    <div>
      <SearchDeviceStatus />
    </div>
    <div>
      <DeviceStatusChart />
    </div>
  </main>
</template>

<script setup>
import SearchDeviceStatus from './components/SearchDeviceStatus.vue';
import DeviceStatusChart from './components/DeviceStatusChart.vue'
</script>

<style scoped>

</style>

<template>
  <header>
    <h1>Device Statuses</h1>
  </header>
  <main>
    <div>
      <SearchDeviceStatus />
    </div>

    <div>
      <SelectedChart @chartChanged="handleChartChanged" />
    </div>

    <div>
      <component :is="selectedChartComponent" />
    </div>

  </main>
</template>

<script setup>
  import SearchDeviceStatus from './components/SearchDeviceStatus.vue';
  import DevicePowerChart from './components/DevicePowerChart.vue';
  import DeviceStatusChart from './components/DeviceStatusChart.vue';
  import SelectedChart from './components/SelectedChart.vue';
  import { ref, shallowRef } from 'vue';
  
  let selectedChartComponent = shallowRef(null);

  const handleChartChanged = (newChart) => {
    switch (newChart) {
      case 'powerChart':
        selectedChartComponent.value = DevicePowerChart;
        break;
      case 'statusChart':
        selectedChartComponent.value = DeviceStatusChart;
        break;
      default:
        selectedChartComponent.value = DevicePowerChart;
    }
  };
  
</script>

<style scoped>
</style>

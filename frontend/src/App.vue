<template>
  <header>
    <h1>Guidewheel Dashboard</h1>
  </header>
  <main>
    <div>
      <Search />
    </div>

    <div>
      <SelectedChart @chartChanged="handleChartChanged" />
    </div>

    <div class = "chart">
      <component :is="selectedChartComponent" />
    </div>

  </main>
</template>

<script setup>
  import Search from './components/Search.vue';
  import DevicePowerChart from './components/DevicePowerChart.vue';
  import DeviceStatusChart from './components/DeviceStatusChart.vue';
  import SelectedChart from './components/SelectedChart.vue';
  import { shallowRef } from 'vue';
  
  let selectedChartComponent = shallowRef(DevicePowerChart);

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

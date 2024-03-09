<template>
  <Pie v-if="hasData" :data="chartData" :options="chartOptions" />
</template>
  
<script setup>
  import { computed } from 'vue';
  import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
  import { Pie } from 'vue-chartjs'
  import useDevices from '@/compositions/useDevices';

  ChartJS.register(ArcElement, Tooltip, Legend)

  const { deviceStatus } = useDevices();

  const hasData = computed(() => deviceStatus?.value?.length > 0);

  const generateRandomColors = (n) => {
    const colors = [];

    for (let i = 0; i < n; i++) {
      const color = '#' + Math.floor(Math.random() * 16777215).toString(16);
      colors.push(color);
    }
    return colors;
  }; 

  const chartData = computed(() => {

    if (!hasData.value) {
      return null;
    }
    return {
      labels: Object.keys(deviceStatus?.countValue || {}),
      datasets: [
        {
          backgroundColor: generateRandomColors(Object.keys(deviceStatus?.countValue || {})?.length || 0),
          data: Object.values(deviceStatus?.countValue || {})
        }
      ]};
  });

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false
  }
</script>
  
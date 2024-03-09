<template>
  <div style="width: 90vw; height: 90vh;">
    <Pie v-if="hasData" :data="chartData" :options="chartOptions" />
  </div>
</template>
  
<script setup>
  import { computed, ref } from 'vue';
  import { Chart as ChartJS, ArcElement, Tooltip, Legend, plugins } from 'chart.js'
  import { Pie } from 'vue-chartjs'
  import useDevices from '@/compositions/useDevices';


  ChartJS.register(ArcElement, Tooltip, Legend, plugins)

  const { deviceStatus } = useDevices();
  const chartKey = ref(0);

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

    chartKey.value += 1;

    const total = Object.values(deviceStatus?.countValue || {}).reduce((sum, value) => sum + value, 0);
    const percentageData = Object.values(deviceStatus?.countValue || {}).map(value => ((value / total) * 100).toFixed(2));

    return {
      labels: Object.keys(deviceStatus?.countValue || {}),
      datasets: [
        {
          backgroundColor: generateRandomColors(Object.keys(deviceStatus?.countValue || {})?.length || 0),
          data: percentageData
        }
      ]};
  });

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      tooltip: {
        callbacks: {
          label: (context) => {
            const label = context.label || '';
            const value = context.formattedValue || '';
            return `${label}: ${value}%`;
          },
        },
      }
    }
  }
</script>
  
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

  const chartData = computed(() => {
    if (!hasData.value) {
      return null;
    }
    return {
      labels: ['VueJs', 'EmberJs', 'ReactJs', 'AngularJs'],
      datasets: [
        {
          backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
          data: [40, 20, 80, 10]
        }
      ]};
  });

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false
  }
</script>
  
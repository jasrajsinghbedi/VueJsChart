DeviceStatusPercentageChart<template>
  <Line v-if="hasData" :data="chartData" :options="chartOptions" />
</template>

<script setup>
import { computed } from 'vue';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'
import useDevices from '@/compositions/useDevices';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

const { deviceStatus } = useDevices();

const hasData = computed(() => deviceStatus?.value?.length > 0);

const chartData = computed(() => {
  if (!hasData.value) {
    return null;
  }

  return {
    labels : deviceStatus.value.map(status => `${status.start}  -  ${status.end}`),
    datasets: [
      {
        label: 'Power',
        backgroundColor: '#f87979',
        data: deviceStatus.value.map(s => s.power)
      }
    ]
  };
});

const chartOptions = {
  responsive: true
}
</script>
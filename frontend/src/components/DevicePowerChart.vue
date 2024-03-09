<template>
  <div style="width: 90vw; height: 90vh;">
    <Line v-if="hasData" :data="chartData" :options="chartOptions" />
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';
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
import { Line } from 'vue-chartjs';
import useDevices from '@/compositions/useDevices';

const chartKey = ref(0);

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

  chartKey.value += 1;

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
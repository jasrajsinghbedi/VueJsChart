<template>
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
    labels : deviceStatus.value.map(status => status.label),
    datasets: [
      {
        label: 'Device Status',
        backgroundColor: '#f87979',
        data: deviceStatus.value.map(s => ({"off": 0, "unloaded": 1, "idle": 2, "loaded": 3}[s.value])),
      }
    ]
  };
});

const chartOptions = {
  responsive: true,
  scales: {
    y: {
      ticks: {
        callback: function(value, index, ticks) {
          return ["Off", "Unloaded", "Idle", "Loaded"][value] || "";
        }
      }
    }
  }
}
</script>
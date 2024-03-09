<template>
    <div v-if="hasData">
      <select v-model="selectedChart">
        <option value="powerChart">Device Power Chart</option>
        <option value="statusChart">Device Status Chart</option>
      </select>
    </div>
</template>

<script setup>
  import useDevices from '@/compositions/useDevices';  
  import { ref, defineProps, defineEmits, computed, watch } from 'vue';

  const { deviceStatus } = useDevices();
  const hasData = computed(() => deviceStatus?.value?.length > 0);
  const selectedChart = ref('powerChart');
  const emits = defineEmits();

  watch(selectedChart, (newValue) => {
    emits('chartChanged', newValue);
  });
</script>

<style scoped>
</style>

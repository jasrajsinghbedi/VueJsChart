<template>
  <div class="search-container">
    <span class="search-item">
      <label>Device:</label>
      <select v-model="deviceId">
        <option :value="null">Select a device...</option>
        <option v-for="device in deviceList" :key="device.id" :value="device.id">{{ device.name }}</option>
      </select>
    </span>
    <span class="search-item">
      <label>Start date:</label>
      <flat-pickr v-model="startDate" :config="datePickerConfig"/>
    </span>
    <span class="search-item">
      <label>End date:</label>
      <flat-pickr v-model="endDate" :config="datePickerConfig"/>
    </span>
    <button :disabled="!searchButtonEnabled" @click="doSearch()">Search <Spinner v-if="loading" :size="12"/></button>
  </div>
</template>

<script setup>
  import { ref, computed } from 'vue';
  import flatPickr from 'vue-flatpickr-component';
  import 'flatpickr/dist/flatpickr.css';

  import Spinner from './icons/Spinner.vue';
  import useDevices from '../compositions/useDevices'

  const { deviceList, searchDeviceStatus } = useDevices();
  const datePickerConfig = {
    enableTime: true,
  };

  const deviceId = ref(null);
  const startDate = ref(null);
  const endDate = ref(null);

  const loading = ref(false);

  const searchButtonEnabled = computed(() => deviceId.value && startDate.value && endDate.value && !loading.value);

  const doSearch = async () => {
    try {
      loading.value = true;
      await searchDeviceStatus({
        deviceId: deviceId.value,
        startDate: startDate.value,
        endDate: endDate.value,
      });
    } finally {
      loading.value = false;
    }
  }
</script>

<style scoped>
.search-container {
  display: flex;
  flex-direction: row;
  @media(max-width: 800px){
    flex-direction: column;

    .search-item {
      margin-bottom: 5px;
      label {
        margin-right: 15px;
      }
    }
  }

  .search-item {
    margin-right: 15px;
    label {
      margin-right: 5px;
    }
  }
}
</style>
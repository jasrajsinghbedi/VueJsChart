import { ref } from 'vue';

const serverUrl = 'https://k1o9g.wiremockapi.cloud'; // move to .env

const deviceList = ref([]);
const deviceStatus = ref(null);

const searchDeviceStatus = async ({ deviceId, startDate, endDate }) => {
  const response = await fetch(`${serverUrl}/device/status`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ deviceId, startDate, endDate }),
  });
  const body = await response.json();
  deviceStatus.value = body;
  // THE MOCK DATA BEING RETURNED
//   [
//     {
//        "label":"3/8/2024, 3:37:31 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:32 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:33 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:34 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:35 PM",
//        "value":"off"
//     },
//     {
//        "label":"3/8/2024, 3:37:36 PM",
//        "value":"off"
//     },
//     {
//        "label":"3/8/2024, 3:37:37 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:38 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:39 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:40 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:41 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:42 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:43 PM",
//        "value":"off"
//     },
//     {
//        "label":"3/8/2024, 3:37:44 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:45 PM",
//        "value":"off"
//     },
//     {
//        "label":"3/8/2024, 3:37:46 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:47 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:48 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:49 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:50 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:51 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:52 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:53 PM",
//        "value":"off"
//     },
//     {
//        "label":"3/8/2024, 3:37:54 PM",
//        "value":"idle"
//     },
//     {
//        "label":"3/8/2024, 3:37:55 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:56 PM",
//        "value":"loaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:57 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:58 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:37:59 PM",
//        "value":"unloaded"
//     },
//     {
//        "label":"3/8/2024, 3:38:00 PM",
//        "value":"unloaded"
//     }
//  ]
};

const fetchDevices = async () => {
  try {
    const response = await fetch(`http://localhost:8080/devices`);
    
    if (!response.ok) {
      throw new Error(`Error: ${response.status} - ${response.statusText}`);
    }

    const body = await response.json();
    deviceList.value = body;
  } catch (error) {
    alert('Error fetching devices:' +  error);
  }
  // THE MOCK DATA BEING RETURNED
  // deviceList.value = [{
  //   "id": "demo_t_afm16",
  //   "name": "demo_t_afm16"
  // }]
}

fetchDevices();

export default function useDevices() {
  return {
    deviceList,
    deviceStatus,
    searchDeviceStatus,
  }
}
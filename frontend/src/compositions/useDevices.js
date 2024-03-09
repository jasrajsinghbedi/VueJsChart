import { ref } from 'vue';

const serverUrl = 'http://localhost:8080'; // move to .env

const deviceList = ref([]);
const deviceStatus = ref(null);

const convertToUnixTimestamp = (dateTime) => {
  const selectedDateObject = new Date(dateTime);
  return Math.floor(selectedDateObject.getTime() / 1000);
};

const searchDeviceStatus = async ({ deviceId, startDate, endDate }) => {

  const unixStartDateTime = convertToUnixTimestamp(startDate);
  const unixEndDateTime = convertToUnixTimestamp(endDate);


  try {
    const queryString = `?deviceId=${encodeURIComponent(deviceId)}&unixStartDateTime=${encodeURIComponent(unixStartDateTime)}&unixEndDateTime=${encodeURIComponent(unixEndDateTime)}`;

    const response = await fetch(`${serverUrl}/device/status${queryString}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    // Handle the response here
    const data = await response.json();
    console.log(data);
    deviceStatus.value = data;
  } catch (error) {
      console.error('Error:', error);
  }
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
}


const searchStatusCount = async ({ deviceId, startDate, endDate }) => {

  const unixStartDateTime = convertToUnixTimestamp(startDate);
  const unixEndDateTime = convertToUnixTimestamp(endDate);

  try {
    const queryString = `?deviceId=${encodeURIComponent(deviceId)}&unixStartDateTime=${encodeURIComponent(unixStartDateTime)}&unixEndDateTime=${encodeURIComponent(unixEndDateTime)}`;

    const response = await fetch(`${serverUrl}/device/status/count${queryString}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    // Handle the response here
    const data = await response.json();
    console.log(data);
    deviceStatus.countValue = data;
  } catch (error) {
      console.error('Error:', error);
  }
  };

fetchDevices();

export default function useDevices() {
  return {
    deviceList,
    deviceStatus,
    searchDeviceStatus,
    searchStatusCount
  }
}
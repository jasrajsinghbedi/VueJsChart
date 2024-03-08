<template>
    <svg
      class="spinner"
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 50 50"
      :width="size"
      :height="size"
    >
      <circle
        class="path"
        cx="25"
        cy="25"
        r="20"
        fill="none"
        stroke-width="4"
        stroke-miterlimit="10"
      />
    </svg>
  </template>
  
  <script>
  export default {
    name: 'Spinner',
    props: {
      size: {
        type: [String, Number],
        default: '50px' // Default size
      }
    }
  };
  </script>
  
  <style scoped>
  .spinner {
    animation: rotate 2s linear infinite;
  }
  
  .path {
    stroke: #333;
    stroke-linecap: round;
    animation: dash 1.5s ease-in-out infinite;
  }
  
  @keyframes rotate {
    100% {
      transform: rotate(360deg);
    }
  }
  
  @keyframes dash {
    0% {
      stroke-dasharray: 1, 150;
      stroke-dashoffset: 0;
    }
    50% {
      stroke-dasharray: 90, 150;
      stroke-dashoffset: -35;
    }
    100% {
      stroke-dasharray: 90, 150;
      stroke-dashoffset: -124;
    }
  }
  </style>
  
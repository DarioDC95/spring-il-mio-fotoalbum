<script>
import axios from 'axios'
import { store } from './store';

export default {
  data() {
    return {
      store
    }
  },
  methods: {
    getPictures() {
      axios.get(store.BASE_API_URL + "/picture/index", {
        params: {

          title: store.searchParam
        }
      })
        .then(response => {
          const pictures = response.data.pictures;
          console.log(pictures);

          store.pictures = pictures;
        })
        .catch(err => console.log(err));
    },
  },
  watch: {
  'store.search'(newVal) {
    if (newVal === true) {
      this.getPictures();
      store.search = false;
    }
  }
},
  mounted() {
    this.getPictures();
  }
}
</script>

<template>
  <router-view />
</template>

<style lang="scss">
@use './styles/general.scss'as *;
</style>

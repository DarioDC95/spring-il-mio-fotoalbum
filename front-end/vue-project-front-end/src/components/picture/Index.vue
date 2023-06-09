<script>
import { store } from '../../store.js';

// components
import ShowPicture from './ShowPicture.vue';

export default {
  components: {
    ShowPicture
  },
  data() {
    return {
      store,
    }
  },
  methods: {
    filterPicturesByTitle() {
      store.search = !store.search;
    }
  },
}
</script>

<template>
  <main>
    <div class="container">
      <div class="row">
        <div class="col">
          <h1 class="mb-3">Lista di Foto</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div class="d-flex justify-content-between">
            <div class="input-group mb-3 w-50">
              <span class="input-group-text" for="name">Nome della Foto</span>
                <input type="text" class="form-control" v-model="store.searchParam" placeholder="nome" aria-label="Username" aria-describedby="basic-addon1"
                name="name">
                <button class="btn btn-success" @click="filterPicturesByTitle()">Search</button>
            </div>
            <div>
              <router-link :to="{ name: 'AddMessage' }" class="btn btn-primary">Send Message</router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="row flex-column">
        <div v-if="store.pictures.length > 0">
          <div v-for="(picture, index) in store.pictures" :key="index">
            <div v-if="picture.visible" class="col-12">
              <ShowPicture :picture="picture"/>
            </div>
          </div>
        </div>
        <div v-else>
          Nessuna Foto presente
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped lang="scss">

</style>

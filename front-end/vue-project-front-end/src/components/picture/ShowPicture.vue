<script>
import { store } from '../../store.js';

export default {
    props: {
        picture: Object,
    },
    data() {
        return {
            store,
            open: false
        }
    },
    methods: {
        openClose() {
            this.open = !this.open
        }
    }
}
</script>

<template>
    <div class="mycard my-3">
        <div class="d-flex justify-content-between mb-3">
            <h3 @click="openClose()">{{ picture.title }}</h3>
        </div>
        <div v-if="open" class="d-flex flex-row border">
            <img class="w-25 rounded me-5" :src="picture.url" :alt="picture.title">
            <div class="d-flex">
                <div>
                    <div class="mb-3">
                        <h4>Descizione:</h4>
                        <span>{{ picture.description }}</span>
                    </div>
                    <div>
                        <h4>Categorie:</h4>
                        <span>{{ (picture.categories).map(a => a.name).join(', ') }}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss">
    .mycard {

        height: 100%;
        
        h3 {
            cursor: pointer;
        }

        .close {
            height: 0;
            overflow: hidden;
            transition: all 1s;
        }
    }
</style>
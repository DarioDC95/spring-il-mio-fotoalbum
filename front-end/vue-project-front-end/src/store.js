import { reactive } from 'vue';

export const store = reactive({
    BASE_API_URL: "http://localhost:8080/api/v1",

    // search pizzas
    search: false,
    searchParam: "",
    pictures: []
})
<script>
import axios from 'axios'
import { store } from '../../store';

export default {
    data() {
        return {
            store,
            pizza: {
                name: '',
                description: '',
                picture: '',
                price: null
            },
        }
    },
    methods: {
        createPizza() {
            axios.post(store.BASE_API_URL + "/pizza-create", this.pizza)
            .then(response => {
                const pizza = response.data.pizza;
                console.log(pizza);
                this.$router.push('/');
            })
            .catch(err => console.log(err));
        }
    },
}
</script>

<template>
    <main>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="w-100 d-flex justify-content-between align-items-center">
						<h1 class="mb-3">Aggiungi la tua pizza</h1>
						<router-link :to="{ name: 'homepage' }" class="btn btn-primary">Back</router-link>
					</div>
				</div>
				<div class="col-12">
					<form @submit.prevent="createPizza()">
						<div class="mb-3">
						  	<label for="name" class="form-label">Nome</label>
						  	<input type="text" class="form-control" placeholder="nome della pizza" 
						  	name="name" v-model="this.pizza.name">
						</div>
						<div class="mb-3">
						  	<label for="description" class="form-label">Descrizione</label>
						  	<input type="text" class="form-control" placeholder="ingredienti" 
						  	name="description" v-model="this.pizza.description">
						</div>
						<div class="mb-3">
						  	<label for="picture" class="form-label">Foto</label>
						  	<input type="text" class="form-control" placeholder="url dell'immagine" 
						  	name="picture" v-model="this.pizza.picture">
						</div>
						<div class="mb-3">
						  	<label for="price" class="form-label">Prezzo</label>
						  	<input type="number" step=".01" class="form-control" placeholder="prezzo della pizza" 
						  	name="price" v-model="this.pizza.price">
						</div>
						<div>
						    <button type="submit" value="CREATE" class="btn btn-primary mb-3">Conferma pizza</button>
						</div>
					</form>
				</div>
			</div>
		</div>		
	</main>
</template>

<style scoped lang="scss">
    
</style>
<script>
import axios from 'axios'
import { store } from '../../store';

export default {
    data() {
        return {
            store,
            contact: {
                email: '',
				message: ''
            },
        }
    },
    methods: {
        createcontact() {
            axios.post(store.BASE_API_URL + "/contact/send", this.contact)
            .then(response => {
                const contact = response.data.contact;
                console.log(contact);
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
						<h1 class="mb-3">Aggiungi la tua contact</h1>
						<router-link :to="{ name: 'homepage' }" class="btn btn-primary">Back</router-link>
					</div>
				</div>
				<div class="col-12">
					<form @submit.prevent="createcontact()">
						<div class="mb-3">
						  	<label for="name" class="form-label">Nome</label>
						  	<input type="email" class="form-control" placeholder="nome della contact" 
						  	name="email" v-model="this.contact.email">
						</div>
						<div class="mb-3">
						  	<label for="name" class="form-label">Nome</label>
						  	<textarea class="form-control" placeholder="nome della contact" 
						  	name="message" v-model="this.contact.message"></textarea>
						</div>
						<div>
						    <button type="submit" value="CREATE" class="btn btn-primary mb-3">Conferma il Messaggio</button>
						</div>
					</form>
				</div>
			</div>
		</div>		
	</main>
</template>

<style scoped lang="scss">
    
</style>
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
			errors: []
        }
    },
    methods: {
		isEmailOk() {
			
			let email = this.contact.email.trim();
			let [a, b] = email.split('@');
			if(b) {

				let [c, d] = b.split('.');
				if(!d) {

					return false;
				}
			} else {

				return false;
			}

			this.contact.email = email;
			return true;
		},
		isMessageOk() {

			let message = this.contact.message.trim();
			if(message == '') {

				return false
			}

			this.contact.message = message;
			return true;
		},
        createcontact() {
			this.errors = [];
			let email = this.isEmailOk();
			let message = this.isMessageOk();
			if(email == false) {

				let object = {
					field: 'email',
					defaultMessage: 'inserisci un dominio valido'
				}
				this.errors.push(object); 
			} 
			if(message == false) {

				let object = {
					field: 'message',
					defaultMessage: 'inserisci un messaggio da inviare'
				}
				this.errors.push(object); 
			} 
			if(email == true && message == true) {

				axios.post(store.BASE_API_URL + "/contact/send", this.contact)
				.then(response => {
					const contact = response.data.contact;
					console.log(contact);
					this.$router.push('/');
				})
				.catch(err => {
					
					console.log(err);
					this.errors = err.response.data.bindingResult;
					console.log(this.errors);
				});
			}
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
						<h1 class="mb-3">Spedisci un messaggio</h1>
						<router-link :to="{ name: 'homepage' }" class="btn btn-primary">Back</router-link>
					</div>
				</div>
				<div class="col-12">
					<form @submit.prevent="createcontact()">
						<div class="mb-3">
						  	<label for="name" class="form-label">Email</label>
						  	<input type="email" class="form-control" placeholder="email" 
						  	name="email" v-model="this.contact.email">
							<div v-for="(error, index) in errors" :key="index">
								<div v-if="error.field == 'email'" class="alert alert-success d-inline-block mt-3">
									{{ error.defaultMessage }}
								</div>
							</div>
						</div>
						<div class="mb-3">
						  	<label for="name" class="form-label">Messaggio</label>
						  	<textarea class="form-control" placeholder="messaggio" 
						  	name="message" v-model="this.contact.message"></textarea>
							<div v-for="(error, index) in errors" :key="index">
								<div v-if="error.field == 'message'" class="alert alert-success d-inline-block mt-3">
									{{ error.defaultMessage }}
								</div>
							</div>
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
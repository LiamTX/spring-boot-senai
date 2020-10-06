<template>
  <div class="home">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->

    <v-card class="card-1 ac mt-13 mb-5 p20" max-width="344" outlined>
      <v-list-item three-line>


        <v-list-item-content class="d-flex flex-column mx-n1 ">
          <h2 class="headline">Sessão de login</h2>
        </v-list-item-content>
      </v-list-item>


      <v-card-text class="ac display-b p-0">
        <v-text-field v-model="userData.username" autocapitalize="none" color="#000000" :rules="emailRules" label="E-mail"></v-text-field>
        <!-- <v-text-field v-model="userData.password" color="#000000" label="Senha" type="password"></v-text-field> -->

        <v-text-field :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.min]"
          :type="show2 ? 'text' : 'password'" name="input-10-2" label="Senha" v-model="userData.password"
          class="input-group--focused" @click:append="show2 = !show2"></v-text-field>

      </v-card-text>


      <v-card-actions>
        <v-btn @click="tryLogin()" color="primary ac display-b mb-2">Entrar</v-btn>

      </v-card-actions>

      <div @click="goToSinup" class="alg-txt-c cp">
        <a>Ainda não tenho cadastro</a>
      </div>
      <!-- <div align="center">
        <v-btn color="primary" text @click="callSupport()">contatar suporte</v-btn>
      </div> -->

    </v-card>
    
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },

  data:() => ({

    // rules: {
    //   required: value => !!value || 'Required.',
    //   min: v => v.length >= 8 || 'Min 8 characters',
    //   emailMatch: () => ('The email and password you entered don\'t match'),
    // },

    url: process.env.VUE_APP_PROD_URL,
    
    show1: false,
    show2: false,

    userData: {
      username: '',
      password: ''
    },

    rules: [
      value => !!value || 'Required.',
      value => (value && value.length >= 3) || 'Min 3 characters',
    ],

    emailRules: [
      v => !!v || 'Precisa preencher.',
      v => /.+@.+/.test(v) || 'E-mail precisa se válido',
    ],

  }),


  methods:{
    tryLogin(){
      console.log(this.userData)
    },


    goToSinup(){
      this.$router.push('/Signup')
    }
  }
}
</script>

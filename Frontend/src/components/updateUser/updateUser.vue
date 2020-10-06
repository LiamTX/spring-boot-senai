<template>
  <div class="about">

    <!-- <v-card class="card-1 ac mt-13 mb-5 p20" max-width="500" outlined> -->

          <v-text-field color="#000000" v-model="userData.name" :rules="rules" label="Nome" ></v-text-field>

        <v-text-field v-model="userData.email" autocapitalize="none" color="#000000" :rules="emailRules" label="E-mail"></v-text-field>

        <!-- <v-text-field :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
        :type="show2 ? 'text' : 'password'" name="input-10-2" label="Nova senha" v-model="userData.password"
        class="input-group--focused" @click:append="show2 = !show2"></v-text-field> -->

        <!-- <v-btn @click="trySignup()" color="primary ac display-b mb-2">Cadastrar</v-btn> -->

        <div class="mt-7 d-flex justify-space-between">
            <v-btn color="error" @click="closeUpdateModal">    
                <v-icon dark left>mdi-close</v-icon>Cancelar
            </v-btn>

            <v-btn color="success" @click="tryUpdate()"><span class="clr-whi">Atualizar</span></v-btn>
        </div>
    <!-- </v-card> -->

  </div>
</template>
<script>
export default {
    props:['propsData'],
    
    data:() => ({

        url: process.env.VUE_APP_PROD_URL,
        
        show1: false,
        show2: false,


        documentRules: [
        value => !!value || 'Necessário.',
        value => (value && value.length >= 11) || 'Min 11 characters',
        ],

        rules: [
        value => !!value || 'Necessário.',
        value => (value && value.length >= 3) || 'Min 3 characters',
        ],

        emailRules: [
        v => !!v || 'Necessário preencher.',
        v => /.+@.+/.test(v) || 'E-mail precisa se válido',
        ],

        userData:{
        name:'',
        email:'',
        password:'',
        document:''
        }
    }),

  
    created(){
        if(this.propsData){this.setInput()}
    },

  methods:{
    setInput(){
        // console.log(this.propsData)
        this.userData.id = this.propsData.id
        this.userData.name = this.propsData.nome
        this.userData.email = this.propsData.email
    },

    tryUpdate(){
      console.log(this.userData)
      this.$emit('tryUpdateUser', this.userData)
    },

    closeUpdateModal(){
        this.$emit('closeUpdateModal', false)
    },
  }
}
</script>
<template>
    <div>



        <v-card class="card-1 ac mt-13 mb-5 p20" max-width="1000" outlined>
            <v-btn color="primary" @click="createUserModal = true">ADICONAR NOVO USUÁRIO</v-btn>

            <div>
                <v-data-table
                :headers="headers"
                :items="desserts"
                item-key="name"
                class="elevation-1"
                :search="search"
                :custom-filter="filterOnlyCapsText"
                >
                    <template v-slot:top>
                        <v-text-field
                        v-model="search"
                        label="Search (SOMENTE LETRAS MAIUSCULAS)"
                        class="mx-4"
                        ></v-text-field>
                    </template>

                <template v-slot:item.actions="{ item }">
                    <v-btn color="primary" outlined @click="edituser(item)">editar</v-btn>
                    <v-btn color="error" class="ml-2" outlined @click="deleteUser(item)">Deletar</v-btn>
                </template>
                </v-data-table>
            </div>

        </v-card>

    <v-dialog
      v-model="updateUserModal"
      persistent
      max-width="500"
    >
    
    <v-card class="p20">
        <v-card-title class="headline">
          Editar usuário
        </v-card-title>
        
            <div>
                <UpdateUser :propsData="selectedUser" @tryUpdateUser="tryUpdateUser" @closeUpdateModal="closeUpdateModal"/>
            </div>

      </v-card>
    
    
    </v-dialog>


     <v-dialog
      v-model="createUserModal"
      persistent
      max-width="500"
    >
    
    <v-card class="p20">
        <v-card-title class="headline">
          Cadastrar novo usuário
        </v-card-title>
        
            <div>
                <SignupUser @trySinupUser="createNewUser" @closeSignupModal="closeSignupModal"/>
            </div>
      </v-card>
    
    
    </v-dialog>
        


    
    </div>
</template>
<script>
import FormFields from '../formFields'

import UpdateUser from '../updateUser/updateUser'
import SignupUser from '../addUser/addUser'

export default {

    components:{
        UpdateUser,
        FormFields,
        SignupUser
    },
    data:() => ({
        url: process.env.VUE_APP_PROD_URL,

        selectedUser:'',
        editUserModal: false,

        createUserModal:false,
        updateUserModal: false,
        search: '',
        calories: '',
        desserts:[]
        // desserts: [
        //   {
        //     name: 'Frozen Yogurt',
        //     email: 'pedro@teste',
        //     cpf: '1234567890',
        //   },
        //   {
        //     name: 'Ice cream sandwich',
        //     email: 'pedro@teste',
        //     cpf: '1234567890',
        //   },
        //   {
        //     name: 'Eclair',
        //     email: 'pedro@teste',
        //     cpf: '1234567890',
        //   },
        // ],
      
    }),

    computed: {
      headers () {
        return [
          {
            text: 'id do usuário',
            align: 'start',
            sortable: false,
            value: 'id',
          },
          {
            text: 'Nome do usuário',
            align: 'start',
            sortable: false,
            value: 'nome',
          },
          {
            text: 'Email do usuário',
            value: 'email',
            filter: value => {
              if (!this.calories) return true

              return value < parseInt(this.calories)
            },
          },
          { text: 'CPF', value: 'cpf' },
          { text: 'ações', value: 'actions' },
        ]
      },
    },

    created(){
      this.listUsers()
    },

    methods:{
        listUsers(){
          this.$http.get(this.url + '/users').then(resp => {
              let results = resp.data
              console.log(results)
              this.desserts = results
          })
        },

        edituser(param){
            this.updateUserModal = true

            this.selectedUser = param
        },

        createNewUser(param){
          let body = {
            nome: param.name,
            email: param.email,
            password: param.password,
            cpf: param.document,
          }
          
          this.$http.post(this.url + '/users', body).then(resp => {
            if(resp.status == 200){
              alert("Usuário registrado com sucesso")
              this.createUserModal = false
              this.listUsers()

            }else{
              alert("Ops algo deu errado com o cadastro")
            }
          })

          .catch(err => {
            alert(err)
            console.log(err)
          })
            
        },

        closeSignupModal(){
          this.createUserModal = false
        },

        tryUpdateUser(param){

          console.log(param)
          let body = {
            nome: param.name,
            email: param.email,
          }
          console.log(body)
          this.$http.put(this.url + `/users/${param.id}`, body).then(resp => {

            if(resp.status == 200){
              alert("Usuário alterado com sucesso")
              this.listUsers()
              this.updateUserModal = false

            }else{
              alert("Ops algo deu errado com a alteração do usuário")
            }
          })

          .catch(err => {
            alert(err)
            console.log(err)
          })
        },

        closeUpdateModal(){
          this.updateUserModal = false
        },

        deleteUser(param){
          console.log(param.id)


            let userId = param.id
            this.$http.delete(this.url + `/users/${userId}`).then(resp => {

            if(resp.status == 200){
              this.listUsers()
              alert("Usuário deletado com sucesso")

            }else{
              alert("Ops algo deu errado ao tentar deletar o usuário")
            }
          })

          .catch(err => {
            alert(err)
            console.log(err)
          })
        },


        filterOnlyCapsText (value, search, item) {
            return value != null &&
            search != null &&
            typeof value === 'string' &&
            value.toString().toLocaleUpperCase().indexOf(search) !== -1
      },
    }

}
</script>
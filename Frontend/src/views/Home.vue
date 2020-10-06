<template>
  <div class="home">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->

    <ListUsers />
    
  </div>
</template>

<script>
// @ is an alias to /src
import ListUsers from '../components/ListUsers/listusers'

export default {
  name: 'Home',
  components: {
    ListUsers
  },

  data:() => ({
  }),


  methods:{

  }
}
</script>

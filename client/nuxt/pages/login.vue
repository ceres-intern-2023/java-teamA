<script setup lang="ts">
const router = useRouter()

const inputForm = reactive({
  email: "labol@example.com",
  password: "password",
})

const login = () => {
  const params = new URLSearchParams();
  params.append("username", inputForm.email)
  params.append("password", inputForm.password)

  fetch('http://localhost:8080/api/login', {
    credentials: 'include',
    method: "POST",
    body: params,
  }).then(res => {
    router.push('/mypage')
  })
}

</script>
<template>
  <h1>Login</h1>
  <div>
    <input type="email" v-model="inputForm.email">
    <input type="password" v-model="inputForm.password">
    <button @click="login()">login</button>
  </div>
</template>
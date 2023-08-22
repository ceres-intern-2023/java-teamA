<script setup lang="ts">
type UserType = {
  userId: number;
  name: string;
  email: string;
};
const users = ref<UserType[]>([]);

fetch("http://localhost:8080/sample/users")
  .then((response) => response.json())
  .then((data) => {
    users.value = data;
  })
  .catch((_) => {});
</script>
<template>
  <div>
    <h1>Top</h1>
    <div>
      <ul>
        <li v-for="user in users">
          {{ user.userId }}/{{ user.name }}/{{ user.email }}
        </li>
      </ul>
    </div>
  </div>
</template>

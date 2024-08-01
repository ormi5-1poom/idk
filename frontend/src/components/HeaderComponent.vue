<script setup>
import router from "@/router";

const goLogin = function () {
    router.push("/login");
}
</script>

<template>
    <div>
        헤더
        <button @click = "goLogin">로그인</button>
    </div>
</template>

<style scoped>

</style>

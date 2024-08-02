<script setup>

import HeaderComponent from "@/components/HeaderComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>

<template>
    <div class="header" v-if="$route.name !== 'Login' && $route.name !== 'Signup'">
        <HeaderComponent/>
    </div>
    <div class="body">
        <RouterView/>
    </div>
    <div class="footer" v-if="$route.name !== 'Login' && $route.name !== 'Signup'">
        <FooterComponent/>
    </div>
</template>

<style>
@font-face {
    font-family: 'Gmarket Regular';
    src: url("@/assets/fonts/GmarketSansTTFMedium.ttf") format('truetype');
}

@font-face {
    font-family: 'Gmarket Bold';
    src: url("@/assets/fonts/GmarketSansTTFBold.ttf") format('truetype');
}

@font-face {
    font-family: 'Gmarket Light';
    src: url("@/assets/fonts/GmarketSansTTFLight.ttf") format('truetype');
}

@font-face {
    font-family: 'Nexon Medium';
    src: url("@/assets/fonts/NEXONLv1GothicRegular.ttf");
}

@font-face {
    font-family: 'Nexon Bold';
    src: url("@/assets/fonts/NEXONLv1GothicBold.ttf");
}

@font-face {
    font-family: 'Nexon Light';
    src: url("@/assets/fonts/NEXONLv1GothicLight.ttf");
}

* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

button {
    cursor: pointer;
}

.header {
    display: flex;
    justify-content: center;
    width: 100vw;
    height: 100px;
    flex-shrink: 0;
}

.footer {
    display: flex;
    justify-content: center;
    width: 100vw;
    height: 100px;
    flex-shrink: 0;
    background-color: #C5CCD2;
    margin-top: 200px;
}

.body {
    flex: 1;
    width: 60vw;
    height: auto;
    display: flex;
    justify-content: center;
}
</style>



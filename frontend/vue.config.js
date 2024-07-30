const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        proxy: {
            '/api': {
                target: "localhost:8080",
                changeOrigin: true
            }
        }
    }
})

const setInterceptors = function (instance) {
    instance.interceptors.request.use(
        (config) => {
            const authHeader = sessionStorage.getItem('authHeader');
            if (authHeader) {
                config.headers['Authorization'] = authHeader;
            }
            return config;
        },
        (error) => {
            return Promise.reject(error);
        }
    )

    instance.interceptors.response.use(
        (response) => {
            return response
        },
        async (error) => {
            return Promise.reject(error);
        }
    )
    return instance
};

export {setInterceptors}

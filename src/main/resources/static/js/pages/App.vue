<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <tariffs-list :tariffs="tariffs" />
        </div>
    </div>
</template>

<script>
    import TariffsList from 'components/tariffs/TariffList.vue'

    export default {
        components: {
            TariffsList
        },
        data() {
            return {
                tariffs: frontendData.tariffs,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>

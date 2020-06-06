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
    import { addHandler } from 'util/ws'
    import { getIndex } from 'util/collections'

    export default {
        components: {
            TariffsList
        },
        data() {
            return {
                tariffs: frontendData.tariffs,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.tariffs, data.id)
                if (index > -1) {
                    this.tariffs.splice(index, 1, data)
                } else {
                    this.tariffs.push(data)
                }
            })
        }
    }

</script>

<style>

</style>

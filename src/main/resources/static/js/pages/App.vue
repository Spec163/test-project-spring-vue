<template>
    <v-app>
        <div>
            <v-app-bar color="deep-purple accent-4" dense dark>

                <v-app-bar-nav-icon></v-app-bar-nav-icon>

                <v-toolbar-title>Мониторинг тарифов</v-toolbar-title>

                <v-spacer></v-spacer>

                <span v-if="profile" style="font-size: 24px">{{profile.name}}</span>
                <v-btn v-if="profile" icon href="/logout">
                    <v-icon>exit_to_app</v-icon>
                </v-btn>
            </v-app-bar>

            <v-content>
                <v-content>
                    <v-container v-if="!profile">
                        Необходимо авторизоваться через
                        <a href="/login">Google</a>
                    </v-container>
                    <v-container v-if="profile">
                        <tariffs-list :tariffs="tariffs" />
                    </v-container>
                </v-content>
            </v-content>
        </div>
    </v-app>
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

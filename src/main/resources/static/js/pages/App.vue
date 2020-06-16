<template>
    <v-app>
        <div>
            <v-app-bar color="deep-purple accent-4" dense dark>

                <v-app-bar-nav-icon v-if="profile"></v-app-bar-nav-icon>

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
                if (data.objectType === 'TARIFF'){
                    const index = this.tariffs.findIndex(item => item.id === data.body.id)
                    switch (data.eventType) {
                        case 'CREATE':
                        case 'UPDATE':
                            if (index > -1) {
                                this.tariffs.splice(index, 1, data.body)
                            } else {
                                this.tariffs.push(data.body)
                            }
                            break
                        case 'REMOVE':
                            this.tariffs.splice(index, 1)
                            break
                        default:
                            console.error(`Event type unknown! "${data.eventType}"`)
                    }
                } else {
                    console.error(`Event or Object type is unknown! "${datadata.objectType}"`)
                }
            })
        }
    }

</script>

<style>

</style>

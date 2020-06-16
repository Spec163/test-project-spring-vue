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
                        <tariffs-list />
                    </v-container>
                </v-content>
            </v-content>
        </div>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import TariffsList from 'components/tariffs/TariffList.vue'
    import { addHandler } from 'util/ws'

    export default {
        components: {
            TariffsList
        },
        computed: mapState(['profile']),
        methods: mapMutations(['addTariffMutation', 'updateTariffMutation', 'removeTariffMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'TARIFF'){
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addTariffMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateTariffMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeTariffMutation(data.body)
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

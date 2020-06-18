<template>
    <v-app>
        <div>
            <!--    или <v-app-bar>     -->
            <v-app-bar app color="deep-purple accent-4" dense dark>

                <v-toolbar-title class="font-size">Мониторинг тарифов</v-toolbar-title>

                <v-btn text v-if="profile" class="font-size"
                       :disabled="$route.path === '/'"
                        @click="showTariffs">
                    Tariffs
                </v-btn>
                <v-spacer></v-spacer>

                <v-btn text v-if="profile" class="font-size"
                       :disabled="$route.path === '/profile'"
                       @click="showProfile">
                    {{profile.name}}
                </v-btn>
                <v-btn v-if="profile" icon href="/logout">
                    <v-icon>mdi-export</v-icon>
                </v-btn>

                <template v-slot:extension v-if="profile">
                    <v-tabs align-with-title>
                        <v-tab @click="showTariffs">Tariffs</v-tab>
                        <v-tab @click="showProfile">Profile</v-tab>
                    </v-tabs>
                </template>
            </v-app-bar>
        </div>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    import router from "../router/router";

    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addTariffMutation', 'updateTariffMutation', 'removeTariffMutation']),
            showTariffs() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            }
        },
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
        },
        // Неавторизованного пользователя перекидывает на /auth
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }

</script>

<style scoped>
    .font-size {
        font-size: 110%;
    }

    .navigate-btn {
        color: #6200ea;
    }

</style>

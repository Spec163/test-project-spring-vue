<template>
    <v-card class="my-2 text-settings" width="1200px">
        <v-content class="yellow darken-3 black--text">
            <div class="main-block">
                <v-card-title primary-title>
                    <h2>{{ tariff.title }}</h2>
                </v-card-title>
                <!--  Запретить показывать кнопки если роль != admin    -->
                <v-card-actions>
                    <v-btn @click="edit" class="ma-2" outlined small fab color="deep-purple accent-4">
                        <v-icon>mdi-pencil</v-icon>
                    </v-btn>
                    <v-btn @click="del" small fab outlined color="deep-purple accent-4">
                        <v-icon color="deep-purple accent-4">delete</v-icon>
                    </v-btn>
                </v-card-actions>
            </div>
            <v-card-text>

                <v-simple-table height="130px" class="yellow darken-3 black--text title">
                    <template v-slot:default>
                        <thead>
                        <tr>
                            <th class="text-left">Стоимость</th>
                            <th class="text-left">Кол-во минут</th>
                            <th class="text-left">Кол-во СМС</th>
                            <th class="text-left">Кол-во интернет трафика</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{{ tariff.price }} руб</td>
                            <td>{{ tariff.calls }} мин</td>
                            <td>{{ tariff.sms }} смс</td>
                            <td>{{ tariff.internet }} Гб</td>
                        </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </v-card-text>
        </v-content>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        props: ['tariff', 'editTariff'],
        methods: {
            ...mapActions(['removeTariffAction']),
            edit() {
                this.editTariff(this.tariff)
            },
            del() {
                this.removeTariffAction(this.tariff)
            }
        }
    }

</script>

<style>
    .main-block {

        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 20px;
    }

    .text-settings {
        font-size: 26px;
        color: black;
    }
</style>
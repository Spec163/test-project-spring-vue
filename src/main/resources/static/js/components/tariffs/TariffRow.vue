<template>
    <v-card class="my-2">
        <v-card-title primary-title>
            <h2>{{ tariff.title }}</h2>
            <v-card-actions>
                <v-btn @click="edit" class="ma-2" outlined small fab color="deep-purple accent-4">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn @click="del" small flat fab outlined color="deep-purple accent-4">
                    <v-icon color="deep-purple accent-4">delete</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card-title>
        <v-card-text>
            {{ tariff.price }} руб
                    {{ tariff.calls }} мин
                    {{ tariff.sms }} смс
                    {{ tariff.traffic }} Гб
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        props: ['tariff', 'editTariff', 'deleteTariff', 'tariffs'],
        methods: {
            edit() {
                this.editTariff(this.tariff)
            },
            del() {
                this.deleteTariff(this.tariff)
            }
        }
    }

</script>

<style>


</style>
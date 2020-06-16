<template>
    <div>
<!--    добавить правило ввода (не меньше 3 символов :rule) https://vuetifyjs.com/en/components/inputs/#inputs  -->
        <v-text-field label="Введите название тарифа" v-model="title" hide-details="auto" />
        <v-text-field label="Введите стоимость" v-model="price" hide-details="auto" />
        <v-text-field label="Введите кол-во минут разговора" v-model="calls" hide-details="auto" />
        <v-text-field label="Введите кол-во СМС" v-model="sms" hide-details="auto" />
        <v-text-field label="Введите кол-во интернет трафика" v-model="internet" hide-details="auto" />
        <v-btn @click="save" class="mt-5 mb-8" block color="red" dark>Добавить тариф</v-btn>
    </div>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        props: ['tariffAttr'],
        data() {
            return {
                title: '',
                price: '',
                calls: '',
                sms: '',
                internet: '',
                id: ''
            }
        },

        watch: {
            tariffAttr(newVal, oldVal) {
                this.title = newVal.title
                this.price = newVal.price
                this.calls = newVal.calls
                this.sms = newVal.sms
                this.internet = newVal.internet
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addTariffAction', 'updateTariffAction']),
            save() {
                const tariff = {id: this.id, title: this.title, price: this.price,
                    calls: this.calls, sms: this.sms, internet: this.internet}


                if (this.id) {
                    this.updateTariffAction(tariff)
                } else {
                    this.addTariffAction(tariff)
                }
                this.title = ''
                this.price = ''
                this.calls = ''
                this.sms = ''
                this.internet = ''
                this.id = ''
            }
        }
    }
</script>

<style>

</style>

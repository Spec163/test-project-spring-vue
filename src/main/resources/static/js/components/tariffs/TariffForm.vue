<template>
    <div>
<!--    добавить правило ввода (не меньше 3 символов :rule) https://vuetifyjs.com/en/components/inputs/#inputs  -->
        <v-text-field label="Введите название тарифа" v-model="title" hide-details="auto" />
        <v-text-field label="Введите стоимость" v-model="price" hide-details="auto" />
        <v-text-field label="Введите кол-во минут разговора" v-model="calls" hide-details="auto" />
        <v-text-field label="Введите кол-во СМС" v-model="sms" hide-details="auto" />
        <v-text-field label="Введите интернет кол-во трафика" v-model="traffic" hide-details="auto" />
        <v-btn @click="save" class="mt-5 mb-8" block color="red" dark>Добавить тариф</v-btn>
    </div>
</template>

<script>
    import { sendTariff } from 'util/ws'

    export default {
        props: ['tariffs', 'tariffAttr'],
        data() {
            return {
                title: '',
                price: '',
                calls: '',
                sms: '',
                traffic: '',
                id: ''
            }
        },

        watch: {
            tariffAttr(newVal, oldVal) {
                this.title = newVal.title
                this.price = newVal.price
                this.calls = newVal.calls
                this.sms = newVal.sms
                this.traffic = newVal.traffic
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                sendTariff({id: this.id, title: this.title, price: this.price,
                    calls: this.calls, sms: this.sms, traffic: this.traffic})
                this.title = ''
                this.price = ''
                this.calls = ''
                this.sms = ''
                this.traffic = ''
                this.id = ''

                /*if (this.id) {
                    this.$resource('/tariff{/id}').update({id: this.id}, tariff).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.tariffs, data.id)
                            this.tariffs.splice(index, 1, data)
                            this.title = ''
                            this.price = ''
                            this.calls = ''
                            this.sms = ''
                            this.traffic = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/tariff{/id}').save({}, tariff).then(result =>
                        result.json().then(data => {
                            this.tariffs.push(data)
                            this.title= ''
                            this.price = ''
                            this.calls = ''
                            this.sms = ''
                            this.traffic = ''
                        })
                    )
                }*/
            }
        }
    }
</script>

<style>

</style>

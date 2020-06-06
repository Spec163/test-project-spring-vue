<template>
    <div>
        <p><input type="text" placeholder="Введите название тарифа" v-model="title" /></p>
        <p><input type="text" placeholder="Введите стоимость" v-model="price" /></p>
        <p><input type="text" placeholder="Введите кол-во минут разговора" v-model="calls" /></p>
        <p><input type="text" placeholder="Введите кол-во СМС" v-model="sms" /></p>
        <p><input type="text" placeholder="Введите интернет кол-во трафика" v-model="traffic" /></p>
        <p><input class="favorite styled-add" type="button" value="Добавить" @click="save" /></p>
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

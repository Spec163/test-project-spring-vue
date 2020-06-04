<template>
    <div>
        <input type="text" placeholder="Write something" v-model="title" />
        <input type="text" placeholder="Write price" v-model="price" />
        <input type="text" placeholder="Write calls" v-model="calls" />
        <input type="text" placeholder="Write sms" v-model="sms" />
        <input type="text" placeholder="Write traffic" v-model="traffic" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }

        return -1
    }

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
                const tariff = { title: this.title, price: this.price,
                    calls: this.calls, sms: this.sms, traffic: this.traffic}

                if (this.id) {
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
                }
            }
        }
    }
</script>

<style>

</style>

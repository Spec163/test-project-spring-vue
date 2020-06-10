<template>
    <v-layout align-content-space-around justify-start column>
        <tariff-form :tariffs="tariffs" :tariffAttr="tariff" />
        <tariff-row v-for="tariff in sortedTariffs"
                     :key="tariff.id"
                     :tariff="tariff"
                     :editTariff="editTariff"
                     :deleteTariff="deleteTariff"
                     :tariffs="tariffs" />
    </v-layout>
</template>

<script>
    import TariffRow from 'components/tariffs/TariffRow.vue'
    import TariffForm from 'components/tariffs/TariffForm.vue'

    export default {
        props: ['tariffs'],
        components: {
            TariffRow,
            TariffForm
        },
        data() {
            return {
                tariff: null
            }
        },
        computed: {
            sortedTariffs() {
                return this.tariffs.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editTariff(tariff) {
                this.tariff = tariff
            },
            deleteTariff(tariff) {
                this.$resource('/tariff{/id}').remove({id: tariff.id}).then(result => {
                    if (result.ok) {
                        this.tariffs.splice(this.tariffs.indexOf(tariff), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>

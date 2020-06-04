<template>
    <div style="position: relative; width: 300px;">
        <tariff-form :tariffs="tariffs" :tariffAttr="tariff" />
        <tariff-row v-for="tariff in tariffs"
                     :key="tariff.id"
                     :tariff="tariff"
                     :editTariff="editTariff"
                     :deleteTariff="deleteTariff"
                     :tariffs="tariffs" />
    </div>
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

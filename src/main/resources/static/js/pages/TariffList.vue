<template>
    <v-container>
        <v-layout align-content-space-around justify-start column>
            <tariff-form :tariffAttr="tariff" />
            <tariff-row v-for="(tariff, index) in sortedTariffs"
                        :key="`tariff.id - ${index}`"
                        :tariff="tariff"
                        :editTariff="editTariff" />
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters } from 'vuex'
    import TariffRow from 'components/tariffs/TariffRow.vue'
    import TariffForm from 'components/tariffs/TariffForm.vue'

    export default {
        components: {
            TariffRow,
            TariffForm
        },
        data() {
            return {
                tariff: null
            }
        },
        computed: mapGetters(['sortedTariffs']),
        methods: {
            editTariff(tariff) {
                this.tariff = tariff
            }
        }
    }
</script>

<style>

</style>

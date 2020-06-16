import Vue from 'vue'
import Vuex from 'vuex'
import tariffsApi from 'api/tariffs'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        tariffs: frontendData.tariffs,
        profile: frontendData.profile
    },
    getters: {
        sortedTariffs: state => state.tariffs.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addTariffMutation(state, tariff) {
            state.tariffs = [
                ...state.tariffs,
                tariff
            ]
        },
        updateTariffMutation(state, tariff) {
            const updateIndex = state.tariffs.findIndex(item => item.id === tariff.id)

            state.tariffs = [
                ...state.tariffs.slice(0, updateIndex),
                tariff,
                ...state.tariffs.slice(updateIndex + 1)
            ]
        },
        removeTariffMutation(state, tariff) {
            const deletionIndex = state.tariffs.findIndex(item => item.id === tariff.id)

            if (deletionIndex > -1) {
                state.tariffs = [
                    ...state.tariffs.slice(0, deletionIndex),
                    ...state.tariffs.slice(deletionIndex + 1)
                ]
            }
        },
    },
    actions: {
        async addTariffAction({commit, state}, tariff) {
            const result = await tariffsApi.add(tariff)
            const data = await result.json()
            const index = state.tariffs.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateTariffMutation', data)
            } else {
                commit('addTariffMutation', data)
            }
        },
        async updateTariffAction({commit}, tariff) {
            const result = await tariffsApi.update(tariff)
            const data = await result.json()
            commit('updateTariffMutation', data)
        },
        async removeTariffAction({commit}, tariff) {
            const result = await tariffsApi.remove(tariff.id)

            if (result.ok) {
                commit('removeTariffMutation', tariff)
            }
        },
    }
})
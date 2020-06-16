import Vue from 'vue'

const tariffs = Vue.resource('/tariff{/id}')

export default {
    add: tariff => tariffs.save({}, tariff),
    update: tariff => tariffs.update({id: tariff.id}, tariff),
    remove: id => tariffs.remove({id})
}
<template>
    <div class="orangered-form">
        <div class="main-block">
            <div class="main-block__section">
                <h2>{{ tariff.title }}</h2>
            </div>
            <div class="main-block__section">
                <button class="favorite styled main-block__button main-block__button_margin-bottom" @click="edit">Редактировать</button>
                <button class="fa fa-trash main-block__button" @click="del"></button>
            </div>
        </div>
        <table class="card tariff-card">
            <thead class="bottom-line">
                <tr>
                    <th>Стоимость</th>
                    <th>Минуты</th>
                    <th>СМС</th>
                    <th><p>Интернет</p></th>
                </tr>
            </thead>
            <tbody class="bottom-line">
                <tr>
                    <td>{{ tariff.price }} руб</td>
                    <td>{{ tariff.calls }} мин</td>
                    <td>{{ tariff.sms }} шт</td>
                    <td>{{ tariff.traffic }} Гб</td>
                </tr>
            </tbody>
        </table><br/>
    </div>
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
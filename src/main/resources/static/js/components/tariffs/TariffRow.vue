<template>
    <div>

        <table>
            <tr>
                <th><i>id</i></th>
                <th>Название</th>
                <th>Цена <p>(за месяц)</p></th>
                <th>Кол-во<p>минут</p><p>(в месяц)</p></th>
                <th>Кол-во<p>смс</p><p>(в месяц)</p></th>
                <th><p>Интернет</p>трафик<p>(в месяц)</p></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <td><i>({{ tariff.id }})</i></td>
                <td>{{ tariff.title }}</td>
                <td>{{ tariff.price }}</td>
                <td>{{ tariff.calls }}</td>
                <td>{{ tariff.sms }}</td>
                <td>{{ tariff.traffic }}</td>
                <td><input type="button" value="Изменить" @click="edit" />
                <td><input type="button" value="Удалить" @click="del" /></td>
            </tr>
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

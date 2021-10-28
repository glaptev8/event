<template>
    <v-menu
            ref="menu1"
            v-model="menu1"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            max-width="290px"
            min-width="auto"
    >
        <template v-slot:activator="{ on, attrs }">
                <v-text-field
                        v-model="dateFormatted"
                        v-bind:label="label"
                        persistent-hint
                        prepend-icon="mdi-calendar"
                        v-bind="attrs"
                        v-on="on"
                ></v-text-field>
        </template>
        <v-date-picker
                v-model="date"
                no-title
                @input="menu1 = false"
        ></v-date-picker>
    </v-menu>
</template>

<script>
    export default {
        name: "DateField",
        props: ['updateDateFormatted', 'updateStartDate', 'formatDate', "label"],
        data() {
            return {
                date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
                dateFormatted: this.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),
                menu1: false,
            }
        },
        watch: {
            date(newVal){
                this.updateStartDate(newVal)
                this.dateFormatted = this.formatDate(newVal)
                this.updateDateFormatted(this.formatDate(newVal))
            }
        },
        methods: {
            parseDate(date) {
                if (!date) return null

                const [month, day, year] = date.split('/')
                return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
            },
        }
    }
</script>

<style scoped>

</style>
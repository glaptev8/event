<template>
    <v-row justify="start">
        <v-dialog
                v-model="dialog"
                width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                        color="primary"
                        dark
                        v-bind="attrs"
                        v-on="on"
                >
                    Create Event
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="text-h5">Create Event</span>
                </v-card-title>
                <v-textarea
                        style="width: 27%"
                        rows="1"
                        auto-grow
                        required
                        v-model="event.description"
                        label="description"
                ></v-textarea>
                <date-field :updateDateFormatted="updateDateFormatted"
                            :updateStartDate="updateStartDate"
                            :format-date="formatDate"
                            :label="`startDate`">
                </date-field>
                <date-field :updateDateFormatted="updateDateFormatted"
                            :updateStartDate="updateEndDate"
                            :format-date="formatDate"
                            :label="`startDate`">
                </date-field>
                <v-select
                        v-model="event.countUser"
                        :items="states"
                        menu-props="auto"
                        label="count user"
                        hide-details
                ></v-select>
                <v-select
                        v-model="event.entertainmentType"
                        :items="entertainmentType"
                        menu-props="auto"
                        item-text="description"
                        item-value="code"
                        label="type"
                        hide-details
                ></v-select>
                <v-select
                        v-model="startTime"
                        :items="time"
                        menu-props="auto"
                        label="start time"
                        hide-details
                ></v-select>
                <v-select
                        v-model="endTime"
                        :items="time"
                        menu-props="auto"
                        label="end time"
                        hide-details
                ></v-select>
                <v-btn
                        class="mr-4"
                        @click="submit"
                >create Event</v-btn>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    import DateField from "components/event/DateField.vue";
    import {mapState} from "vuex"

    export default {
        name: "EventCreateForm",
        components: {DateField},
        computed: mapState({
            entertainmentType: state => state.dictionary.EntertainmentType
        }),
        data() {
            return {
                states: [...Array(1000).keys()].map(i => i + 1),
                time: Array(24 * 4).fill(0).map((_, i) => { return ('0' + ~~(i / 4) + ':0' + 60  * (i / 4 % 1)).replace(/\d(\d\d)/g, '$1') }),
                event: {
                    description: '',
                    startDate: '',
                    endDate: '',
                    countUser: '',
                    entertainmentType: ''
                },
                startDate: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
                endDate: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
                dateFormatted: this.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),
                startTime: '',
                endTime: '',
                dialog: false,
            }
        },
        methods: {
            updateDateFormatted(newVal) {
                this.dateFormatted = newVal
            },
            updateStartDate(newVal) {
                this.startDate = newVal;
            },
            updateEndDate(newVal) {
                this.endDate = newVal;
            },
            formatDate(date) {
                if (!date) return null

                const [year, month, day] = date.split('-')
                return `${month}/${day}/${year}`
            },
            submit() {
                this.event.startDate = this.startDate + " " + this.startTime;
                this.event.endDate = this.endDate + " " + this.endTime;
                this.$resource('/v1/api/event/create').save(this.event).then(result => {
                    if (result.ok) {
                        this.event.startDate += " " + this.startTime;
                        this.event.endDate += " " + this.endTime;
                        this.dialog = false;
                        console.log(result);
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
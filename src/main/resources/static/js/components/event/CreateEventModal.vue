<template>
    <v-row justify="start">
        <v-dialog
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
        </v-dialog>
    </v-row>
</template>

<script>
    export default {
        name: "CreateEventModal",
        data() {
            return {
                    event: {
                        description: '',
                        entertainmentType: '',
                        startDate: '',
                        endDate: '',

                }
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>
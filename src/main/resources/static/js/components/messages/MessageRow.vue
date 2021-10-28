<template>
    <div>
        {{message.text}}
        <span style="position: absolute; right: 0">
                <input type="button" value="Edit" @click="edit"/>
                <input type="button" value="X" @click="del"/>
            </span>
    </div>
</template>

<script>
    export default {
        props: ['message', 'editMethod', 'deleteMethod', 'messages'],
        methods: {
            edit() {
                this.editMethod(this.message)
            },
            del() {
                this.deleteMethod({id: this.message.id})
            }
        }
    }
</script>

<style>

</style>
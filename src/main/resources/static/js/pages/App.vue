<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title :xs6="!$vuetify.breakpoint.xsOnly" >echat</v-toolbar-title>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path==='/'"
                   @click="showMessages"
            >
                Messages
            </v-btn>
            <div class="flex-grow-1"></div>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path ==='/profile'"
                   @click="showProfile"
            >
                {{profile.name}}
            </v-btn>
            <v-btn v-if="profile" href="/logout" icon>
                <v-icon>E</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>

            <router-view></router-view>
        </v-content>

    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import {addHandler} from 'util/ws'

    export default {
        computed: mapState(['profile']),
        methods:
            {
                ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
                showMessages() {
                    this.$router.push('/')
                },
                showProfile() {
                    this.$router.push('/profile')
                }
            },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>
</style>
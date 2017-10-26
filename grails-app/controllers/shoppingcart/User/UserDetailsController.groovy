package shoppingcart.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

//@Transactional(readOnly = true)
class UserDetailsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserDetails.list(params), model:[userDetailsCount: UserDetails.count()]
    }

    def show(UserDetails userDetails) {
        respond userDetails
    }

    def create() {
        respond new UserDetails(params)
    }


    def save(UserDetails userDetails) {
        if (userDetails == null) {

            notFound()
            return
        }

        if (userDetails.hasErrors()) {

            respond userDetails.errors, view:'create'
            return
        }


        if(!userDetails.save(flush:true)){
            flash.message = message(code: 'default.created.message', args: [message(code: 'userDetails.label', default: 'userDetails')])
        }else {
            flash.message = "this ${userDetails.id} has been created "+userDetails.firstName
        }
        redirect view: "index"

    }

    def edit(UserDetails userDetails) {
        respond userDetails
    }


    def update(UserDetails userDetails) {
        if (userDetails == null) {
            notFound()
            return
        }
        if (userDetails.hasErrors()) {
            respond userDetails.errors, view:'edit'
            return
        }

        if(!userDetails.save(flush:true)){
            flash.message = message(code: 'default.updated.message', args: [message(code: 'userDetails.label', default: 'userDetails'), userDetails.id])
        }else {
            flash.message = "this ${userDetails.id} has been updated "+userDetails.firstName
        }
        redirect view: "index"

    }


    def delete(UserDetails userDetails) {
        if (userDetails == null) {
            notFound()
            return
        }

        if(userDetails.delete(flush:true)) {
            flash.message = "No data "
        }else {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userDetails.label', default: 'userDetails'), userDetails.id])
        }
        redirect view: "index"
    }

    protected void notFound() {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), params.id])
                redirect action: "index"
     }
}

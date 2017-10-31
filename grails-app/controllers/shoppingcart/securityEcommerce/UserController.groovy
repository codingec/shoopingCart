package shoppingcart.securityEcommerce

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }

    def show(User user) {
        respond user
    }

    @Transactional(readOnly = true)
    def create() {
        respond new User(params)
    }


    def save(User user) {
        if (user == null) {
            notFound()
            return
        }
        if (user.hasErrors()) {
            respond user.errors, view:'create'
            return
        }
         if(!user.save(flush:true)){
             flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User')])
         }else {
             flash.message = "this ${user.id} has been created "+user.username
         }
             redirect view: "index"

    }

    def edit(User user) {
        respond user
    }


    def update(User user) {
        if (user == null) {
            notFound()
            return
        }
        if (user.hasErrors()){
            respond user.errors, view:'edit'
            return
        }
        if(!user.save(flush:true)){
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
        }else {
                flash.message = "this ${user.id} has been updated "+user.username
       }
        redirect view: "index"

    }


    def delete(User user) {
        if (user == null) {
            notFound()
            return
        }
        if(user.delete(flush:true)) {
            flash.message = "No data "
            }else {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
        }
        redirect view: "index"
    }

    protected void notFound() {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: "index"
    }

}

package shoppingcart.Client

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured


class ClientsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Secured(['permitAll'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Clients.list(params), model:[clientsCount: Clients.count()]
    }

    def show(Clients clients) {
        respond clients
    }

    def create() {
        respond new Clients(params)
    }


    def save(Clients clients) {
        if (clients == null) {
            notFound()
            return
        }
        if (clients.hasErrors()) {
            respond clients.errors, view:'create'
            return
        }

        if(!clients.save(flush:true)){
            flash.message = message(code: 'default.created.message', args: [message(code: 'clients.label', default: 'Clients')])
        }else {
            flash.message = "this ${clients.id} has been created "+clients.name
        }
        redirect view: "index"
    }

    def edit(Clients clients) {
        respond clients
    }


    def update(Clients clients) {
        if (clients == null) {
            notFound()
            return
        }

        if (clients.hasErrors()) {
            respond clients.errors, view:'edit'
            return
        }

        if(!clients.save(flush:true)){
            flash.message = message(code: 'default.updated.message', args: [message(code: 'clients.label', default: 'Clients'), clients.id])
        }else {
            flash.message = "this ${clients.id} has been updated "+clients.name
        }
        redirect view: "index"
    }


    def delete(Clients clients) {
        if (clients == null) {
            notFound()
            return
        }

        if(clients.delete(flush:true)) {
            flash.message = "No data "
        }else {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'clients.label', default: 'Clients'), clients.id])
        }
        redirect view: "index"
    }

    protected void notFound() {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clients.label', default: 'Clients'), params.id])
                redirect action: "index"

    }
}

package shoppingcart.Client

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
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

    @Transactional
    def save(Clients clients) {
        if (clients == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clients.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clients.errors, view:'create'
            return
        }

        clients.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clients.label', default: 'Clients'), clients.id])
                redirect clients
            }
            '*' { respond clients, [status: CREATED] }
        }
    }

    def edit(Clients clients) {
        respond clients
    }

    @Transactional
    def update(Clients clients) {
        if (clients == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clients.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clients.errors, view:'edit'
            return
        }

        clients.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clients.label', default: 'Clients'), clients.id])
                redirect clients
            }
            '*'{ respond clients, [status: OK] }
        }
    }

    @Transactional
    def delete(Clients clients) {

        if (clients == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        clients.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clients.label', default: 'Clients'), clients.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clients.label', default: 'Clients'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package shoppingcart.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
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

    @Transactional
    def save(UserDetails userDetails) {
        if (userDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userDetails.errors, view:'create'
            return
        }

        userDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), userDetails.id])
                redirect userDetails
            }
            '*' { respond userDetails, [status: CREATED] }
        }
    }

    def edit(UserDetails userDetails) {
        respond userDetails
    }

    @Transactional
    def update(UserDetails userDetails) {
        if (userDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userDetails.errors, view:'edit'
            return
        }

        userDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), userDetails.id])
                redirect userDetails
            }
            '*'{ respond userDetails, [status: OK] }
        }
    }

    @Transactional
    def delete(UserDetails userDetails) {

        if (userDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userDetails.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), userDetails.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

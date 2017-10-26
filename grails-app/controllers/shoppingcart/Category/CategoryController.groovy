package shoppingcart.Category

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class CategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Category.list(params), model:[categoryCount: Category.count()]
    }

    def show(Category category) {
        respond category
    }

    def create() {
        respond new Category(params)
    }


    def save(Category category) {
        if (category == null) {
            notFound()
            return
        }
        if (category.hasErrors()) {
            respond category.errors, view:'create'
            return
        }

        if(!category.save(flush:true)){
            flash.message = message(code: 'default.created.message', args: [message(code: 'category.label', default: 'Category')])
        }else {
            flash.message = "this ${category.id} has been created "+category
        }
        redirect view: "index"
    }

    def edit(Category category) {
        respond category
    }


    def update(Category category) {
        if (category == null) {
            notFound()
            return
        }
        if (category.hasErrors()) {
            respond category.errors, view:'edit'
            return
        }

        if(!category.save(flush:true)){
            flash.message = message(code: 'default.updated.message', args: [message(code: 'category.label', default: 'Category'), category.id])
        }else {
            flash.message = "this ${category.id} has been updated "+category
        }
        redirect view: "index"
    }

    def delete(Category category) {
        if (category == null) {
            notFound()
            return
        }
        if(category.delete(flush:true)) {
            flash.message = "No data "
        }else {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'category.label', default: 'Category'), category.id])
        }
        redirect view: "index"
    }

    protected void notFound() {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
                redirect action: "index", method: "GET"
    }
}

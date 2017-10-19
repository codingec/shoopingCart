package shoppingcart
import shoppingcart.securityEcommerce.Requestmap
import shoppingcart.securityEcommerce.Role
import shoppingcart.securityEcommerce.User
import shoppingcart.securityEcommerce.UserRole

class BootStrap {

    def init = { servletContext ->

        print "HERE WE ARE"
        def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')

        def user = User.findOrSaveWhere(username: 'rsilva@gmail.com', password:  'password')
        if(!user.authorities.contains(adminRole)){
            UserRole.create(user, adminRole, true)
        }
        //SPRING SECURITY URL MAPPING
        new Requestmap(url: '/timeline', configAttribute: 'ROLE_USER').save()
        new Requestmap(url: '/person/*', configAttribute: 'IS_AUTHENTICATED_REMEMBERED').save()
        new Requestmap(url: '/post/followAjax', configAttribute: 'ROLE_USER').save()
        new Requestmap(url: '/post/addPostAjax', configAttribute: 'ROLE_USER,IS_AUTHENTICATED_FULLY').save()
        new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
    }
    def destroy = {
    }
}

package shoppingcart.User

import shoppingcart.securityEcommerce.User

class UserDetails {

    User user
    String firstName
    String lastName
    String emailAddress
    String country
    String address
    String number
    int age
    String contractId
    String nationalId
    Date startContract
    Date endContract

    static constraints = {
        firstName : nullable: false
        lastName : nullable: false
        emailAddress : nullable: false
        country : nullable: true
        address : nullable: true
        number : nullable: true
        age : nullable: true
        contractId : nullable: false
        nationalId : nullable: true
        startContract : nullable: false
        endContract : nullable: false

    }
    static mapping = {
//        version false
//        datasource 'shoppingcart'
    }


}

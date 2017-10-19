
//Added by the Spring Security InterceptUrlMap
grails.plugins.springsecurity.SecurityConfigType
grails.plugins.springsecurity.securityConfigType.InterceptUrlMap
grails.plugins.springsecurity.securityConfigType.Requestmap
//spring security HASHING METHOD
grails.plugin.springsecurity.password.algorithm = 'bcrypt'
//reject if no rule
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
//URL LOGIN
//grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/user'
//HANDELING SESSION WITH SPRING SECURITY
grails.plugin.springsecurity.useSessionFixationPrevention = true
// Added by the Spring Security Core plugin:
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'shoppingcart.securityEcommerce.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'shoppingcart.securityEcommerce.UserRole'
grails.plugin.springsecurity.authority.className = 'shoppingcart.securityEcommerce.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/dbconsole/**',   access: ['ROLE_ADMIN']],
		[pattern: '/monitoring',     access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.ipRestrictions = [
		[pattern: '/pattern1/**', access: '123.234.345.456'],
		[pattern: '/pattern2/**', access: '10.0.0.0/8'],
		[pattern: '/pattern3/**', access: ['10.10.200.42', '10.10.200.63']]
]

environments {
	development {
		images = "/${userHome}/shoppingImages/"
		files = "/${userHome}/shoppingExcel/"
		saleReport = "/${userHome}/shoppingPdf/"

	}
	production {
		images = "/${userHome}/shoppingImages/"
		files = "/${userHome}/shoppingExcel/"
		saleReport = "/${userHome}/shoppingPdf/"
	}
}
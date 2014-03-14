class UrlMappings {

	static mappings = {
        "/phones"(controller: "phone", action:'index')
        "/phones/$id"(controller: "phone", action:'show')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

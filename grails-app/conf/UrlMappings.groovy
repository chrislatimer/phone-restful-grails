class UrlMappings {

	static mappings = {
        "/phones"(resources:"phone")
        "/phones/$id"(controller:"phone", action:"show")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

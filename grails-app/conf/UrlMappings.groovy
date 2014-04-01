class UrlMappings {

	static mappings = {
        "/phones"(resources:"phone")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/phones/$id"{
            productType="phone"
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

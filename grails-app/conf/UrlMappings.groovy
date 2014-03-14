class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/phones/$id"{
            [controller: "phone", action:"show"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

(ns pedestal-todo-list.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer [response-for]]
            [io.pedestal.http :as bootstrap]
            [pedestal-todo-list.service :as service]
            [midje.sweet :refer [fact facts]]
            [midje.checkers :refer [contains]]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(facts "home page test"
  (fact
    (:body (response-for service :get "/")) => "Hello Felipe!")

  (fact
    (:headers (response-for service :get "/")) =>
    {"Content-Type"                      "text/html;charset=UTF-8"
     "Strict-Transport-Security"         "max-age=31536000; includeSubdomains"
     "X-Frame-Options"                   "DENY"
     "X-Content-Type-Options"            "nosniff"
     "X-XSS-Protection"                  "1; mode=block"
     "X-Download-Options"                "noopen"
     "X-Permitted-Cross-Domain-Policies" "none"
     "Content-Security-Policy"           "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"}))

(facts "about-page-test"
  (fact "check clojure version"
    (:body (response-for service :get "/about")) => (contains "Clojure 1.10.1"))

  (fact "check headers"
    (:headers (response-for service :get "/about"))
    => {"Content-Type"                      "text/html;charset=UTF-8"
        "Strict-Transport-Security"         "max-age=31536000; includeSubdomains"
        "X-Frame-Options"                   "DENY"
        "X-Content-Type-Options"            "nosniff"
        "X-XSS-Protection"                  "1; mode=block"
        "X-Download-Options"                "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy"           "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"}))

(ns pedestal-todo-list.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer [response-for]]
            [io.pedestal.http :as bootstrap]
            [pedestal-todo-list.service :as service]
            [midje.sweet :refer [fact facts]]
            [midje.checkers :refer [contains]]
            [cheshire.core :as json]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(facts "home page test"
  (fact "body is a map"
    (-> service
        (response-for :get "/hello")
        :body
        (json/parse-string true))
    => {:hello "Felipe!"})

  (fact "expected headers"
    (:headers (response-for service :get "/hello")) =>
    {"Content-Type"                      "application/json;charset=UTF-8"
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
    => {"Content-Type"                      "text/plain"
        "Strict-Transport-Security"         "max-age=31536000; includeSubdomains"
        "X-Frame-Options"                   "DENY"
        "X-Content-Type-Options"            "nosniff"
        "X-XSS-Protection"                  "1; mode=block"
        "X-Download-Options"                "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy"           "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"}))

(facts "todo endpoint"
  (fact "content-type is json"
    (-> service
        (response-for :get "/")
        :headers
        (get "Content-Type"))
    => (contains "application/json"))

  (fact "body checking"
    (-> service
        (response-for :get "/")
        :body
        (json/parse-string true))
    => [{:name "lavar a louça"
         :done false}]))

(facts "add task"
  (fact "item 2"
    (-> service
        (response-for :post "/"
                      :headers {"Content-Type" "application/json"}
                      :body (json/encode "{\"name\":\"item 2\"}"))
        :body
        (json/parse-string true))
    => [{:name "lavar a louça"
         :done false}
        {:name "item 2"
         :done false}]))

;(facts "Reset endpoint"
;  (fact "reset state"
;    (-> service
;        (response-for :post "/"
;                      :headers {"Content-Type" "application/json"}
;                      :body (json/encode "{\"name\":\"item 2\"}")))
;    (-> service
;        (response-for :get "/reset"))
;    ;   :body
;    ;   (json/parse-string true))
;    => [{:name "lavar a louça"
;         :done false}]))



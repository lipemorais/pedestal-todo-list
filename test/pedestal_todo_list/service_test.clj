(ns pedestal-todo-list.service-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [pedestal-todo-list.service :as service]))


(fact
 (1 => 2))


(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(deftest home-page-test
  (is (=
       (:body (response-for service :get "/"))
       "Hello Felip!"))
  (is (/ 1 0))
  (is (=
       (:headers (response-for service :get "/"))
       {"Content-Type"                      "text/html;charset=UTF-8"
        "Strict-Transport-Security"         "max-age=31536000; includeSubdomains"
        "X-Frame-Options"                   "DENY"
        "X-Content-Type-Options"            "nosniff"
        "X-XSS-Protection"                  "1; mode=block"
        "X-Download-Options"                "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy"           "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))


(deftest about-page-test
  (is (.contains
       (:body (response-for service :get "/about"))
       "Clojure 1.10.1"))
  (is (=
       (:headers (response-for service :get "/about"))
       {"Content-Type"                      "text/html;charset=UTF-8"
        "Strict-Transport-Security"         "max-age=31536000; includeSubdomains"
        "X-Frame-Options"                   "DENY"
        "X-Content-Type-Options"            "nosniff"
        "X-XSS-Protection"                  "1; mode=block"
        "X-Download-Options"                "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy"           "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))


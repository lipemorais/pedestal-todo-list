(ns pedestal-todo-list.routes
  (:require [ring.util.response :as ring-resp]
            [io.pedestal.http.route :as route]
            [pedestal-todo-list.state :as state]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [cheshire.core :as json]))

;; Defines "/" and "/about" routes with their associated :get handlers.
;; The interceptors defined after the verb map (e.g., {:get home-page}
;; apply to / and its children (/about).
(def common-interceptors [(body-params/body-params) http/json-body])

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))
(defn home-page
  [request]
  (ring-resp/response {:hello "Felipe!"}))

(defn todo-list
  [request]
  (ring-resp/response state/todo-list-state))

;(defn todo-list-reset
;  []
;  (state/init)
;  (state/state))

(defn todo-list-create
  [request]
  (let
   [task-name (:name (json/parse-string (:json-params request) true))
    _ (state/add-task state/todo-list-state task-name)]
   (ring-resp/response (state/state))))

;; Tabular routes
(def routes #{["/hello" :get (conj common-interceptors `home-page)]
              ["/about" :get (conj common-interceptors `about-page)]
              ["/" :get (conj common-interceptors `todo-list)]
              ;["/reset" :get (conj common-interceptors `todo-list-reset)]
              ["/" :post (conj common-interceptors `todo-list-create)]})

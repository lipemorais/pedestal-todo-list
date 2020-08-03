(ns pedestal-todo-list.state)

(def todo-list-state)

(defn state
  []
  todo-list-state)

(defn init
  []
  (def todo-list-state
    [{:name "lavar a louça"
      :done false}]))


(defn add-task
  [state task-name]
  (def todo-list-state (conj state {:name task-name
                                    :done false}))
  todo-list-state)

(init)
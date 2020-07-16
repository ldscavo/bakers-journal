(ns baking-diary.handler-test
  (:require [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [baking-diary.handler :as bd]))

(deftest test-app
  (testing "main route"
    (let [response (bd/app (mock/request :get "/"))]
      (is (= (:status response) 200))))

  (testing "not-found route"
    (let [response (bd/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

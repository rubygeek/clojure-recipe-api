(ns recipe-api.sample-data-test
  (:require [clojure.test :refer :all]
            [recipe-api.sample-data :refer :all]))

(deftest test-testify-data
  (testing "Adding TEST in front of :name value"
    (is (= {:name "TEST hello"} (testify-data {:name "hello"})))))

(deftest test-testify-all-data
  (testing "Adding TEST in front of a bunch of data"
    (is (= [{:name "TEST hello"}
            {:name "TEST bye"}] 
           (testify-all-data [{:name "hello"}
                              {:name "bye"}])))))

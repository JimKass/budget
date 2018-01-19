(ns budget.core-test
  (:require [clojure.test :refer :all]
            [budget.core :refer :all])
  (:import (java.io FileNotFoundException)))

(deftest read-payslip-test
  (testing "is a function"
    (is (fn? read-payslip)))

  (testing "takes a file and returns and integer"
    (is (= 225.54 (read-payslip "src/budget/PAYSLIP191217.htm"))))

  (testing "will throw an exception if file is not exist"
    (is (thrown-with-msg?
          java.io.FileNotFoundException
          #"src/budget/PAYSLIP191217.html (No such file or directory)"
          (read-payslip "src/budget/PAYSLIP191217.html")))))

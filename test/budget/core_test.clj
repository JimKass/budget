(ns budget.core-test
  (:require [clojure.test :refer :all]
            [budget.core :refer :all])
  (:import (java.io FileNotFoundException)
           (java.util.regex Pattern)))

(defn re-quoted [s]
  (re-pattern (Pattern/quote s)))

(defn float=
  [x y]
  (<= (Math/abs ^float (- x y)) 0.01))

(deftest read-payslip-test
  (testing "is a function"
    (is (fn? get-amount-paid)))

  (testing "takes a file and returns and integer"
    (is (float= 225.54 (get-amount-paid "src/budget/PAYSLIP191217.htm"))))

  (testing "will throw an exception if file is not exist"
    (is (thrown-with-msg?
          java.io.FileNotFoundException
          ( re-quoted "src/budget/PAYSLIP191217.html (No such file or directory)")
          (get-amount-paid "src/budget/PAYSLIP191217.html")))))

(deftest get-payslips-test
  (testing "is a function"
    (is (fn? get-payslips-from-drive))))

; TODO: Make up more tests for get-payslips

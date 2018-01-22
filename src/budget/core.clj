(ns budget.core
  (require [clojure-mail.core :refer :all]
           [clojure-mail.gmail :as gmail]
           [clojure-mail.message :as message]))

(defn read-payslip
  [filename]
  (let [htm-code (slurp filename)
        payout-flag "<TD CLASS=\"netPayDetail\" VALIGN=\"top\" align=\"right\">\\$<\\/TD><TD CLASS=\"netPayDetail\" align=\"right\">"
        re-finder (re-pattern (str "(?<=" payout-flag ").+?(?=<\\/TD>)"))
        payout (re-find re-finder htm-code)]
    (Float/parseFloat payout)))

(defn get-payslips
  [user pswd]
  (let [store (store "imap.gmail.com" user pswd)
        payslips (search-inbox store [:from "payroll@ardentleisure.com"])]
    payslips))

(defn -main
  []
  (println "I do nothing right now."))

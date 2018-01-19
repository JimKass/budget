(ns budget.core)

(defn read-payslip
  [filename]
  (let [pdf-code (slurp filename)
        payout-flag "<TD CLASS=\"netPayDetail\" VALIGN=\"top\" align=\"right\">$</TD><TD CLASS=\"netPayDetail\" align=\"right\">"
        re-finder (re-pattern (str "(?=" payout-flag ").+?(?<=/<//TD/>)"))
        payout (re-find re-finder pdf-code)]
    payout))

(defn -main
  []
  (read-payslip))

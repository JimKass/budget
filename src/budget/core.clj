(ns budget.core
  (require [clojure-mail.core :refer :all]
           [google-apps-clj.credentials/default-credential]
           [google-apps-clj.google-drive :as drive]))

(defn get-amount-paid
  [htm-code]
  (let [payout-flag "<TD CLASS=\"netPayDetail\" VALIGN=\"top\" align=\"right\">\\$<\\/TD><TD CLASS=\"netPayDetail\" align=\"right\">"
        re-finder (re-pattern (str "(?<=" payout-flag ").+?(?=<\\/TD>)"))
        payout (re-find re-finder htm-code)]
    (Float/parseFloat payout)))

(defn get-date
  [htm-code]
  (let [date-flag "<TD>\nPaid on Date\n</TD>\n<TD>\n&nbsp;\n\n"
        re-finder (re-pattern (str "(?<=" date-flag ").+?(?=<\\/TD)"))
        date (re-find re-finder htm-code)]
    date))

(defn date-to-int
  [year month day]
  (let [days-by-month {:1 0 :2 31 :3 59 :4 90 :5 120 :6 151 :7 181 :8 212 :9 243 :10 273 :11 304 :12 334}]
    (+ day
       (+ (* year 365)
          (quot (- year 1) 4))
       (+ (days-by-month month)
          (if (and (= 0 (quot year 4)) (> month 2))
            1
            0)))))

(defn sort-dates
  [dates]
  ; Sorts by year, then month and then day
  ; Most recent to oldest
  (sort-by (fn [date]
             (let [year (take-last 2 date)
                   day (take 2 date)
                   month ()]
               (date-to-int year month day)))
           dates))

(defn get-payslips-from-drive
  []
  (let [scope [com.google.api.services.drive.DriveScopes/DRIVE]
        creds (google-apps-clj.credentials/default-credential scope)]
    (doseq [file (drive/folder-list-files-query "1ByuFGb6lY7S2Rlcn-CHRPz8uDP44eI50")]
      (drive/download-file! file))))

(defn -main
  []
  (println "I do nothing right now."))

(defn is_leap_year [year]
  (not (or (% year 4) (and (not (% year 100)) (% year 400))))
  )

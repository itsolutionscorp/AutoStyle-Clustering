class Gigasecond
   SECONDS_IN_GIGASECOND = 10**9
   SECONDS_IN_HOUR = 3600
   HOURS_IN_DAY = 24
   SECONDS_IN_DAY = SECONDS_IN_HOUR * HOURS_IN_DAY
   FULL_DAYS_IN_GIGASECOND = SECONDS_IN_GIGASECOND / SECONDS_IN_DAY

   def self.from(date)
      return date + FULL_DAYS_IN_GIGASECOND
   end

end
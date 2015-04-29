class Gigasecond
   SECONDS_IN_DAY = 86400
   DAYS_IN_GIGASECOND = 10**9 / SECONDS_IN_DAY

   def self.from(date)
      return date + DAYS_IN_GIGASECOND
   end
end

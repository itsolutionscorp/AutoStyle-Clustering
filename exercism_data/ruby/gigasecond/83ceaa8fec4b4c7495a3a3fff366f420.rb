class Gigasecond
   SECONDS_IN_DAY = 86400
   FULL_DAYS_IN_GIGASECOND = 10**9 / SECONDS_IN_DAY

   def self.from(date)
      return date + FULL_DAYS_IN_GIGASECOND
   end

end

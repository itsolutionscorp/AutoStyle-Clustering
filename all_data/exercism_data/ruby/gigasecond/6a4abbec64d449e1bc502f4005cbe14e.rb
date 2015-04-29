class Gigasecond
   @gs = 10**9
   @seconds_in_hour = 3600
   @hours_in_day = 24

   def self.from(date)
      seconds_in_day = @seconds_in_hour * @hours_in_day
      full_days = @gs / seconds_in_day
      return date + full_days
   end

end

module Gigasecond
  def self.from date 
    seconds_in_day = 60 * 60 * 24
    days_to_add = 10**9 / seconds_in_day
    date + days_to_add
  end
end

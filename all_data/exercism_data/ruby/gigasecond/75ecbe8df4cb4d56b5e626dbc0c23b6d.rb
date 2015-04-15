module Gigasecond
  require 'date'
  GIGASECOND = 10**9
  
  def Gigasecond.from(date)
    new_time = date.to_time + GIGASECOND
    new_time.to_date
  end
end

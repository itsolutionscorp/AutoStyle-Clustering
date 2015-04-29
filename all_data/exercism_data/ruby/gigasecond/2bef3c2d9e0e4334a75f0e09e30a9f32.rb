module Gigasecond
  DAYSECS  = 60*60*24
  INTERVAL = (10**9)/DAYSECS
  
  def Gigasecond.from(date)
    date + INTERVAL  
  end
end

require 'time'
require 'date'

class Gigasecond
  def self.from(date)
    #date.strftime("%Y-%m-%d") + (10**9)/(24*60*60)
    #puts date
    if date.class == Time
      time = date + 10**9
      Date.new(time.year, time.mon, time.mday)
    else
      date + (10**9)/(24*60*60)
      #date + 10**9 * 60
    end
  end
end

require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    
    time = date.to_time
    time = time + (10**9)

    return Date.parse(time.to_s)
  end
end

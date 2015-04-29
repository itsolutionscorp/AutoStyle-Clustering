require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    time = date.to_time
    time = time + (1000000000)
    Date.parse(time.to_s)
  end
end

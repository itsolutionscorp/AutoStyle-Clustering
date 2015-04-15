require 'time'
require 'date'

class Gigasecond
  def self.from(date)
    start_time = Time.parse(date.to_s)
    giga_time = 10**9
    start_time+=giga_time
    Date.parse(start_time.to_s)
  end
end

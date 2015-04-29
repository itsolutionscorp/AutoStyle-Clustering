require 'date'
require 'time'

class Gigasecond
  def self.from(start_time)
    start_time = start_time.to_time
    seconds = 10**9
    end_time = start_time + seconds
    return end_time.to_date
  end
end

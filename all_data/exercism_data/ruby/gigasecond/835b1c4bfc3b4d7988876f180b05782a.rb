require 'date'
require 'time'

class Gigasecond
  def self.from(time)
    (time.to_time + 10**9).utc
  end
end

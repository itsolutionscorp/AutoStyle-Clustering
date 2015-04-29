require 'time'
class Gigasecond
  def self.from(time_in_utc)
    time_in_utc + 1000000000
  end
end

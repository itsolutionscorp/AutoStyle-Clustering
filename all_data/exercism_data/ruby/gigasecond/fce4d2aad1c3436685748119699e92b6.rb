require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    time = Time.at(date.to_time.to_i + 10**9)
    Date.new(time.year, time.month, time.day)
  end
end

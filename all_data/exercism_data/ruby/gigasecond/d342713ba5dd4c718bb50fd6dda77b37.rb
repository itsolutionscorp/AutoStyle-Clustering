require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    time = Time.gm(date.year, date.month, date.day) + 10**9
    return Date.new(time.year, time.month, time.day)
  end
end

require 'date'
require 'time'

class Gigasecond
  def self.from date
    time = Time.new(date.year, date.month, date.day) + 1_000_000_000
    Date.new time.year, time.month, time.day
  end
end

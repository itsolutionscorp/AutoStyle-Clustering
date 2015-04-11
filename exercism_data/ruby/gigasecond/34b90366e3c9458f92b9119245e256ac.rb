require 'date'
require 'time'

class Gigasecond
  GIGA = 10**9
  def self.from(date)
    date.to_time + GIGA
  end
end

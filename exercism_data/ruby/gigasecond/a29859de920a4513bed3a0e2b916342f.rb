require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    date + (10**9 - 6400) / 86400
  end
end

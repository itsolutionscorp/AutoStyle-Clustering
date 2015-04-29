require 'date'
require 'time'

class Gigasecond
  def self.from(t)
    t = t + (10**9)
  end
end

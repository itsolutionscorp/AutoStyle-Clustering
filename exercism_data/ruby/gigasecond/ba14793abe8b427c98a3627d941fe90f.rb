require 'date'
require 'time'

class Gigasecond
  GIGASECOND     = 10**9
  SECONDS_IN_DAY = 24 * 3600

  class << self
    def from(date)
      date + (GIGASECOND / SECONDS_IN_DAY)
    end
  end
end

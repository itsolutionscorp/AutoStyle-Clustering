require 'date'
require 'time'

class Gigasecond
  class << self
    def from(date)
      date + 10**9
    end
  end
end

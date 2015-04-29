require 'date'
require 'time'

class Gigasecond
    def self.from(current)
        new = (current.to_time + (10**9)).to_date
        return new
    end
end

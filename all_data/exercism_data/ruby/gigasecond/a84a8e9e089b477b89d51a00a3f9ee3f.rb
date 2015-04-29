require 'date'
require 'time'

class Gigasecond
    def self.from(date)
        case date
        when Date
            date + 10**9 / 60 / 60 / 24
        when Time
            Date.parse (date + 10**9).to_s
        end
    end
end

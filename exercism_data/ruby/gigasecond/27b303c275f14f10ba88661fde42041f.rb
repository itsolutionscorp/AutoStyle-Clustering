require 'date'
require 'time'

class Gigasecond
    def self.from(d)
        if defined? d.to_time
            d = d.to_time
        end
        (d.to_time + 10**9).to_date
    end
end

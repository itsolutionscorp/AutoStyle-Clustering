require 'time'

OneGigasecond = 10**9

class Gigasecond
    def self.from(start_date)
        (start_date.to_time + OneGigasecond).to_date
    end
end

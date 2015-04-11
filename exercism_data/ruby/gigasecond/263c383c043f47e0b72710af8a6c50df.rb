require 'date'

class Gigasecond
    def self.from(date)
        # Add the number of days to the date (86400 seconds in a day)
        date + (10**9 / 86400)
    end
end

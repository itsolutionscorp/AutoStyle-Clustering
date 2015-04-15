require 'date'

class Gigasecond
    def self.from(date)
        date + 1000000000 /(24*60*60)
    end
end

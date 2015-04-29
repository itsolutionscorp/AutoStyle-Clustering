require 'time'

class Gigasecond
    def self.from(date)
        gigatime = Time.new(date.year, date.month, date.day) + 1_000_000_000
        Date.new(gigatime.year, gigatime.month, gigatime.day)
    end
end

require 'time'

class Gigasecond
    def self.from(time)
        return time + self.gigasecond
    end

    def self.gigasecond()
        10**9
    end
end

class Gigasecond
    def self.from(time)
        Time.at(time + 10**9)
    end
end

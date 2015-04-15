class Gigasecond
    def self.from(start)
        Time.at(start.to_i + 1000000000)
    end
end

class Gigasecond
    def self.from(current)
        return (current.to_time + (10**9)).to_date
    end
end

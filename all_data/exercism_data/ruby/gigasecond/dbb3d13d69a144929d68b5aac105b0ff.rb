class Gigasecond
    def self.from(start)
        return (start.to_time + 10**9).to_date
    end
end

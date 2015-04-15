class Gigasecond
    def self.from(from)
        (from.to_time + 10**9).to_date
    end
end

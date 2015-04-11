class Gigasecond
    def self.from(date)
        t = date.to_time
        t += 10 ** 9
        t.to_date
    end
end

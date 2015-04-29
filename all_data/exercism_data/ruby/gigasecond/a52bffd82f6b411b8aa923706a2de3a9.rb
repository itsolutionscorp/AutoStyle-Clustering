class Gigasecond
    def self.from(date)
        gigasecond = 10 ** 9
        secondsPerDay = 60 * 60 * 24
        date += gigasecond / secondsPerDay
        return date
    end
end

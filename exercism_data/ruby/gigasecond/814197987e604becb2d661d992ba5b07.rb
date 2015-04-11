class Gigasecond
    SECONDS_PER_DAY=86400
    ONE_GIGASECOND=10**9

    def self.from(date)
        date + ONE_GIGASECOND/SECONDS_PER_DAY
    end

end

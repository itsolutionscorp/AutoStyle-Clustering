class Gigasecond
    GIGASECOND = 10**9
    
    def self.from(date_or_time)
        (date_or_time.to_time + GIGASECOND).to_date
    end
end

class Gigasecond
    GIGASECONDS = 10**9
    
    def self.from(date)
        return (date.to_time + GIGASECONDS).to_date
    end
end

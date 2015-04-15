# gigasecond

class Gigasecond
    GIGASECOND_IN_DAYS = 1000000000 / (24*60*60)
    
    def self.from(date)
        return date + GIGASECOND_IN_DAYS
    end
end

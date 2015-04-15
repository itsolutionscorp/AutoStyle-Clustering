class Gigasecond
    GS = 1000000000
    GS_IN_DAYS = GS / 3600 / 24
    
    def self.from birth_day
        birth_day + GS_IN_DAYS
    end
end

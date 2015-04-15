module Gigasecond
    GIGASECOND_IN_DAYS = 10**9
    def self.from(dt)
        (dt.to_time + GIGASECOND_IN_DAYS).to_date
    end
end

require 'date'

class Gigasecond
    attr_reader :date, :start

    SECONDS_PER_HOUR = 3600

    HOURS_PER_DAY = 24

    DAYS_PER_GIGASECOND = 1_000_000_000 / SECONDS_PER_HOUR / HOURS_PER_DAY
    
    def initialize(start = Date.today)
        @start = start
        @date = @start + DAYS_PER_GIGASECOND
    end

end

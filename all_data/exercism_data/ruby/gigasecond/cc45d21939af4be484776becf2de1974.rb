class Gigasecond

    def initialize(date)
        @date = date
        @gs = 1_000_000_000
    end

    def date
        gsBday = @date.to_time + @gs 
        gsBday.to_date
    end
end

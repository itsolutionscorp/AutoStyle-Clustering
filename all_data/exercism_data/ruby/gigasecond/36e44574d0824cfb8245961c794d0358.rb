class Gigasecond

    GigaSecondsInDays = 1000000000 / (3600 * 24)

    def initialize(d)
        @initialDate = d
    end

    def date
        return @initialDate + GigaSecondsInDays
    end

end

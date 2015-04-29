require 'date'

class Gigasecond
    attr_reader :date

    GIGASECOND_TO_DAYS = 10**9 / (24 * 60.0**2)

    def initialize(date)
        @date = (date.to_datetime + GIGASECOND_TO_DAYS).to_date
    end

end

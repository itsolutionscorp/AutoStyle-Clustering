require 'date'
require 'time'

module Gigasecond
    SECONDS_PER_DAY = 86400
    GIGASECOND_IN_DAYS = 1_000_000_000 / SECONDS_PER_DAY

    def Gigasecond.from date
        date + GIGASECOND_IN_DAYS
    end
end

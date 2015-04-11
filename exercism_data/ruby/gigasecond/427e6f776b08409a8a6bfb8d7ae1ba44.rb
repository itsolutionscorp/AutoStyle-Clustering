require 'date'

class Gigasecond
    attr_reader :date

    GIGASECOND = 10**9 

    def initialize(date)
        @date = (date.to_time + GIGASECOND).to_date
    end

end

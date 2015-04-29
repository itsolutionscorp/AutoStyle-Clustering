require 'date'
require 'time'

class Gigasecond

    def self.from(date)
        #puts date

        gs = 10**9
        mins = gs/60
        hours = mins/60
        days = hours/24

        date.next_day(days)

    end

end


puts Gigasecond.from(Date.new(2026,07,31))

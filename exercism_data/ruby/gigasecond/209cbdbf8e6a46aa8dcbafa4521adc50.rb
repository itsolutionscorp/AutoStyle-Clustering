require 'time'

class Gigasecond
    def Gigasecond.from(date)
        gigasecond = 1e9

        Time.at(date.to_time + gigasecond).to_date;
    end
end

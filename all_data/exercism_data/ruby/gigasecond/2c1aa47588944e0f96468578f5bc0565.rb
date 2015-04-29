require 'time'

class Gigasecond
    @@seconds = 1e9 # 1000000000 seconds

    def Gigasecond.from(date)
        Time.at(date.to_time + @@seconds).to_date;
    end
end

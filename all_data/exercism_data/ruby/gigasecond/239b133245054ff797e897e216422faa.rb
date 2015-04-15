require 'date'
require 'time'

class Gigasecond
  class << self
    def from(date)
      date_from(time_from(date) + gigasecond)
    end

    def gigasecond
      10**9
    end

    def time_from(date)
      date.to_time
    end

    def date_from(time)
      time.to_date
    end

    private :gigasecond, :time_from, :date_from
  end
end

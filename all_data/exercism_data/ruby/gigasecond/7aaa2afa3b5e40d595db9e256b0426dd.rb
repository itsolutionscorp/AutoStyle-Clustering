require 'date'
require 'time'

class Gigasecond

  GIGASECOND = 10**9

  class << self
    def from(date)
      date_from(time_from(date) + GIGASECOND)
    end

    def time_from(date)
      date.to_time
    end

    def date_from(time)
      time.to_date
    end

    private :time_from, :date_from
  end
end

module Gigasecond
  class << self
    SECONDS    = 10**9
    WHOLE_DAYS = SECONDS / 60 / 60 / 24

    def from(moment)
      moment.is_a?(Date) ? from_date(moment) : from_time(moment)
    end

    private
    
    def from_date(date)
      Date.jd(date.jd + WHOLE_DAYS)
    end

    def from_time(time)
      (time + SECONDS).to_date
    end
  end
end

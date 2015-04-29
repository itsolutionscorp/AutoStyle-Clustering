class Seconds
  def initialize(seconds)
    @seconds = seconds
    @seconds_in_day = 86400
  end
  
  def in_days
    @seconds/@seconds_in_day
  end

  def date_from(day)
    day + in_days
  end
end

class Gigasecond < Seconds
  def self.from(day)
    Seconds.new(10**9).date_from(day)
  end
end

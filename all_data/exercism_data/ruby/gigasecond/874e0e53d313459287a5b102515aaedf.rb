require 'date'

class Gigasecond
  SECONDS_IN_A_DAY = 86400
  DAYS_PER_GIGASECOND = Rational(10**9,SECONDS_IN_A_DAY)
  def self.from(start)
    if Time == start.class
      (start + 10**9).to_date
    else
      result_with_seconds = start + DAYS_PER_GIGASECOND
      result_with_seconds - result_with_seconds.day_fraction
    end
  end
end

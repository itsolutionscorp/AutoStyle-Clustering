require 'date'

class Gigasecond

  SECONDS_PER_DAY = 60 * 60 * 24
  DAYS_PER_GIGASECOND = (10**9 / SECONDS_PER_DAY).to_i

  def initialize(date)
    @start = date
  end

  def date
    @start += DAYS_PER_GIGASECOND
  end

end

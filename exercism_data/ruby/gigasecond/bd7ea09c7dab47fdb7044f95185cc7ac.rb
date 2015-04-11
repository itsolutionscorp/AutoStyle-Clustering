require 'time'

class Gigasecond
  GIGASECOND = (10**9)

  def initialize(date)
    @time = date.to_time
  end

  def date
    (@time + GIGASECOND).to_date
  end
end

require 'date'
require 'time'

class Gigasecond
  def initialize(date)
    @date = date
    @gigasec_days = 11574
  end

  def date
    return @date.+ @gigasec_days
  end
end

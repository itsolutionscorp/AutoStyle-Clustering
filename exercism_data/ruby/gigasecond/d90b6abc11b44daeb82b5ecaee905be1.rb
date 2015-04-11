require 'date'
require 'time'

class Gigasecond

  def initialize(date)
    @time = date.to_time + 1000000000
  end

  def date
    date = @time.to_date
  end

end

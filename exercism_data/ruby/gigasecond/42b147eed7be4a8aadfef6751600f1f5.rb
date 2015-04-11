require 'date'
require 'time'
class Gigasecond

  def initialize(date)
    @start = date
  end

  def date
    @start + 11574
  end

end

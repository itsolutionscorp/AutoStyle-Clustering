require 'date'
require 'time'

class Gigasecond
  def initialize(date)
      @date = date
  end

  def date
    @date + 11574
    #1000000000 seconds /60/60/24 = 11574.074074074 days
  end
end

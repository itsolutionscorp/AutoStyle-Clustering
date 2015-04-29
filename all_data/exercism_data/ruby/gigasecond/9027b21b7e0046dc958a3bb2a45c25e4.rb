require 'date'
require 'time'

class Gigasecond
  def initialize(argDate)
    @start = argDate
    return date
  end

  def date
    gs = 10**9
    min = 60
    hr = 60
    day = 24

    days = gs/(min*hr*day)

    giga_date = @start + days
  end

end

# Gigasecond.new(Date.new(2011, 4, 25))

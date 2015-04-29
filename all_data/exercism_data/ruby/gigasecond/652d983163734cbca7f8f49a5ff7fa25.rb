require 'date'
require 'time'

class Gigasecond

  def initialize(date)
    @start = Time.new(date.year, date.mon, date.mday)
  end

  def date
    @time = @start + 1000000000
    Date.new(@time.year, @time.mon, @time.mday)
  end

end

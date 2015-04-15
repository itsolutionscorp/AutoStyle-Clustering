require 'time'
require 'date'

class Gigasecond
  attr_accessor :time
  def initialize(date)
    @time = date.to_time
  end

  def date
    gigasecond = @time + (10 ** 9)
    Date.new(gigasecond.year, gigasecond.month, gigasecond.day)
  end
end

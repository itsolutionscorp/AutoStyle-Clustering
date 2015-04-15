require 'date'
require 'time'

class Gigasecond
  def initialize(date_input)
    @date_input = date_input
  end

  def date
    @date_input + (gigaseconds/seconds_to_days)
  end

  def seconds_to_days
    3600 * 24
  end

  def gigaseconds
    10**9
  end
end

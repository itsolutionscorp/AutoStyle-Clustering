require 'date'
require 'time'

class Gigasecond
  def initialize(date)
    @date = date.to_time
  end

  def date
    Time.at(@date.to_i + 10**9).to_date
  end
end

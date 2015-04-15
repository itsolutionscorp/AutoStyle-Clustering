require "date"
require "time"

class Gigasecond
  def initialize date
    @date = date
  end

  def date
    date_seconds = @date.to_time.to_i 
    future_seconds = date_seconds + 10**9

    Time.at(future_seconds).to_date
  end
end

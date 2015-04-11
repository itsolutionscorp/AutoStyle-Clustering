require 'date'
require 'time'

class Gigasecond

  @seconds = 10**9
  def self.from(date)
    return_date = (date.to_time + @seconds).to_date
  end

end

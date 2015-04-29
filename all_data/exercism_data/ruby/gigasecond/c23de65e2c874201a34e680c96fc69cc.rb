require 'date'
require 'time'

class Gigasecond
  def self.from(start_date)
    start_date=start_date.to_time
    start_date+=10**9
    return start_date.to_date
  end
end

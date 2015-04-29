require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    @date_time = date.to_time
    @date_time += 1000000000
    @date_time.to_date
  end
end

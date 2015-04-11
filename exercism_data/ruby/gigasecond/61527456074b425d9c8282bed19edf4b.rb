require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    new_time = date.to_time + (1000000000)
    new_time.to_date
  end
end

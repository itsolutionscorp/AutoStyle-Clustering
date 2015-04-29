require 'date'
require 'time'

class Gigasecond
  GIGA_SECONDS = 10 ** 9
  def self.from(time)
    (time.to_time + GIGA_SECONDS).to_date
  end
end

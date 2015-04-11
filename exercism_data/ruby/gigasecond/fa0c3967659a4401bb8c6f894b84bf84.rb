require 'date'
require 'time'

class Gigasecond
  
  GIGA_SEC = 1_000_000_000.0
  
  def self.from(date_or_time)
    Time.at(date_or_time.to_time.to_i + GIGA_SEC).to_date
  end
  
end

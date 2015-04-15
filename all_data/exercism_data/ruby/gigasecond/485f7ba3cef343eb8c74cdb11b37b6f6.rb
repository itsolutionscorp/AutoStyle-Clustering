require 'date'
require 'time'

class Gigasecond
  #Add a gigasecond
  def self.from(date_time)
    (date_time.to_time + 10**9).to_date
  end
end

require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    gigasecond = 10**9
    (date.to_time + gigasecond).to_date
  end
end

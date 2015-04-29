require 'date'
require 'time'

class Gigasecond
  GIGASECOND = 10**9

  def self.from(date)
    (date.to_time + GIGASECOND).to_date
  end
end

require 'date'
require 'time'

class Gigasecond

  GIGASECOND = 10**9 # seconds

  def self.from(date)
    (date.to_time + GIGASECOND).to_date
  end

end

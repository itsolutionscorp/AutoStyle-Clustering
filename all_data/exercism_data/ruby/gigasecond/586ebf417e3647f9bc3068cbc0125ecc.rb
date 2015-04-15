require 'date'
require 'time'

class Gigasecond
  BILLION = 1_000_000_000
  SECONDS_IN_DAY = 24 * 60 * 60

  def self.from(date)
    date + BILLION/SECONDS_IN_DAY
  end

end

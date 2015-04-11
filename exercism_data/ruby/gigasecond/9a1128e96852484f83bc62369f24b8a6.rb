require 'date'
require 'time'

class Gigasecond
  GIGASECOND_DAYS = 1_000_000_000 / (24 * 60 * 60)

  def self.from(date)
    date + GIGASECOND_DAYS
  end

end

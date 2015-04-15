require 'date'
require 'time'

class Gigasecond
  BILLION = 1_000_000_000

  def self.from(date)
    Date.strptime((date.to_time.utc.to_i + BILLION).to_s, '%s')
  end

end

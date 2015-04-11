require 'date'
require 'time'

class Gigasecond
  GIGASECONDS = 1_000_000_000

  def self.from(date)
    Time.at(date.to_time.to_i + GIGASECONDS).to_date
  end
end

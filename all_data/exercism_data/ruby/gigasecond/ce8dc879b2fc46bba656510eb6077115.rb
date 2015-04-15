require 'date'
require 'time'

module DateArithmetic

  def add_seconds(seconds)
    Time.at(self.to_time.to_i + seconds).to_date
  end

end

class Gigasecond
  def self.from(origin)
    origin.extend DateArithmetic
    origin.add_seconds 1e9
  end

end

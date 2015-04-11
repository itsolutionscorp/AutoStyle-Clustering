require 'date'
require 'time'

module DateArithmetic
  extend self

  def add_seconds(date, seconds)
    Time.at(date.to_time.to_i + seconds).to_date
  end

end


class Gigasecond
  include DateArithmetic

  def self.from(origin)
    DateArithmetic.add_seconds(origin, 1e9)
  end

end

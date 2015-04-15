require 'date'
require 'time'

class Gigasecond
  ONE_GIGASECOND = 1000000000

  def self.from(date)
    Date.parse((date.to_time + (ONE_GIGASECOND)).to_s)
  end
end

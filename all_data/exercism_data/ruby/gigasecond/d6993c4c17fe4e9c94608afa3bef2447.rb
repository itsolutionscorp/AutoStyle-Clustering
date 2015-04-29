require 'date'
require 'time'

class Gigasecond

  GIGASECOND = 1000000000/(60*60*24)

  def self.from(start)
    return start + GIGASECOND
  end

end

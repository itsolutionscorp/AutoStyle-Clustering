require 'time'

class Gigasecond
  GIGASECOND = 10 ** 9

  def self.from(time)
    return time + GIGASECOND
  end
end

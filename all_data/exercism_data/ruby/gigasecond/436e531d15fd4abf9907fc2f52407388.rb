require 'Time'

class Gigasecond

  A_GIGASECOND = 10**9

  def self.from(time)
    time + A_GIGASECOND
  end
end

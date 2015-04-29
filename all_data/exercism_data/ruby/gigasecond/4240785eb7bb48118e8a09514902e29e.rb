require 'date'
require 'time'

class Gigasecond

  def self.from time
    time + Gigasecond.as_n_seconds
  end

  def self.as_n_seconds
    10 ** 9
  end

end

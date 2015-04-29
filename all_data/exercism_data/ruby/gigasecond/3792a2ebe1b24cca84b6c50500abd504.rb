require 'date'
require 'time'

GIGASECOND = 10**9

class Gigasecond
  def self.from (d)
    (d.to_time + GIGASECOND).to_date
  end
end

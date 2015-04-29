require 'date'
require 'time'
GS = 1_000_000_000

class Gigasecond
  def self.from(t)
    if t.is_a? Date
      origin_time = t.to_time
    else
      origin_time = t
    end
    return (origin_time + GS).to_date
  end
end

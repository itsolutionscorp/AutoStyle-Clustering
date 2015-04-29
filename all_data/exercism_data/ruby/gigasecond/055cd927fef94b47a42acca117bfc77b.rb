require 'time'
require 'date'

class Gigasecond
  def self.from(from)
    (from.to_time + 1_000_000_000).to_date
  end
end

require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    (date.to_time + 1_000_000_000).to_date
  end
end

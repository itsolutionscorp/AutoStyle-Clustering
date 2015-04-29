require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    datetime = date.to_time
    (datetime + 1_000_000_000).to_date
  end
end

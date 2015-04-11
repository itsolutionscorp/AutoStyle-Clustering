require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    Time.at(date.to_time.to_i + 1_000_000_000).to_date
  end
end

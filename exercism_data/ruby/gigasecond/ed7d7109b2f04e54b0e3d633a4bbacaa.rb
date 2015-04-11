require "date"
require "time"

class Gigasecond

  def self.from date
    seconds = date.to_time + 1_000_000_000
    Time.at(seconds).to_date
  end

end

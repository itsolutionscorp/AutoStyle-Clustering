require "date"
require "time"

class Gigasecond
  def self.from(date)
    gigaseconds = 10**9
    (date.to_time + gigaseconds).to_date
  end
end

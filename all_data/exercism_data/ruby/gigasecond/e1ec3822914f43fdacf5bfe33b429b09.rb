require 'time'
require 'date'

class Gigasecond
  def self.from(date)
    secs = date.to_time.to_i + 1_000_000_000
    return Time.at(secs).to_date
  end
end

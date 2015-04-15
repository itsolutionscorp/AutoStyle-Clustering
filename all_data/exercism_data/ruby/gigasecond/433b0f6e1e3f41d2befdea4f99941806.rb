require 'date'
require 'time'

class Gigasecond
  def self.from(start_date)
    start_time = start_date.to_time
    final_date = (start_time + 1_000_000_000).to_date
  end
end

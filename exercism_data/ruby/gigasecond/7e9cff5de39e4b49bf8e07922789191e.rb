require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    # Convert to Time Object that can add seconds and convert back to Date Object
    (date.to_time + 10**9).to_date
  end
end

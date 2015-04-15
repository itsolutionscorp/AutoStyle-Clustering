require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    conversion_from_seconds_to_days = 1.0 / (60 * 60 * 24)  # 1 day = 86,400 seconds
    gigasecond_in_days = 10**9 * conversion_from_seconds_to_days
    date + gigasecond_in_days.round
  end

end

require 'time'
require 'date'

class Gigasecond

  def self.from(date)
    time = date.to_time #converts date to time
    (time + 10**9).utc
  end

end

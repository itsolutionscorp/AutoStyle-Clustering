require 'date'
require 'time'

class Gigasecond
  def self.from date

    gigasecond = 10**9

    time = date.to_time.to_i

    Date.parse(Time.at(time + gigasecond).to_s)
  end
end

require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    anniversary = date.to_time + gigasecond
    anniversary.to_date
  end

  def self.gigasecond
    10**9
  end
end

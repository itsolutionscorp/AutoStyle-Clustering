require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    gigasecond_birthday = Time.at(date.to_time + (10**9)).to_date
  end
end

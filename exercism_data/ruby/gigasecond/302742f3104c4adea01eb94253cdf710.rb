require 'date'
require 'time'

class Gigasecond
  def self.from date
    Time.at(date.to_time.to_i + number).to_date
  end

  def self.number
    10**9
  end
end

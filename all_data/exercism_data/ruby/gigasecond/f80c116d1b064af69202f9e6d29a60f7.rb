require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    Time.at(date.to_i + 1000000000)
  end
end

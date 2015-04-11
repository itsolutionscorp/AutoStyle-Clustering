require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    Time.at(Time.parse(date.to_s).to_i + 10**9).to_date
  end
end

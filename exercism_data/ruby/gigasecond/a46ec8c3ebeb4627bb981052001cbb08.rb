require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    Date.parse(Time.at(Time.parse(date.to_s).to_i + 1_000_000_000).to_s)
  end
end

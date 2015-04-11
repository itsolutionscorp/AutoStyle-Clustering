require 'time'
class Gigasecond
  def self.from(time)
    Time.at(time.to_i + 1_000_000_000) 
  end
end

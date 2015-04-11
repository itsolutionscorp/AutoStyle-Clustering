require 'date'

class Gigasecond
  def self.from(date)
    date.to_time + 10**9
  end
end

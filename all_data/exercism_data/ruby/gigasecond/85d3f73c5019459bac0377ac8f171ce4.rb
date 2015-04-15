require 'date'

class Gigasecond
  def self.from date
    date + (10**9 / 3600 / 24)
  end
end

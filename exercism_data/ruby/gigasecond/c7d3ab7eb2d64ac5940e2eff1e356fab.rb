require 'date'

class Gigasecond
  def self.from(date)
    date.next_day((10 ** 9)/(24 * 60 * 60))
  end
end

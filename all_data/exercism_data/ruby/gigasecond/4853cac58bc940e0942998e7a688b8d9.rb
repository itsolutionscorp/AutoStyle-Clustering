require 'date'

class Gigasecond
  def self.from(date)
    gigasecond = 10 ** 9
    days = gigasecond / 86400
    date + days
  end
end

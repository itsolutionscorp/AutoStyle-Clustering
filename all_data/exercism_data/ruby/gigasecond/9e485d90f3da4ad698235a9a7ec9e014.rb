require 'date'

class Gigasecond
  def self.from(date)
    daysToAdd = 10**9/(60*60*24)
    return date + daysToAdd
  end
end

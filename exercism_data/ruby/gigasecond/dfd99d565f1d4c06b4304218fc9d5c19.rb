require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    days_to_add = (((10**9) / 60) / 60) / 24
    return date + days_to_add
  end
end

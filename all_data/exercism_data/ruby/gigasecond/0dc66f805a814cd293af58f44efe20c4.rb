require 'date'
require 'time'

class Gigasecond
  def self.from (date)
    anniversaryDate = date + (10**9)
    return anniversaryDate
  end
end

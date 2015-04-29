require 'date'
require 'time'

class Gigasecond
  def self.from (date)
    gigasecs = 10 ** 9
    anniversaryDate = date + gigasecs
  end
end

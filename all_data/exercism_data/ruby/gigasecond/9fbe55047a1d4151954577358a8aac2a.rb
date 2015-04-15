require 'date'
require 'time'

class Gigasecond
  def self.from(date)
   date + (10**9)/86400
  end
end

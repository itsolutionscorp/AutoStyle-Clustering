require 'date'
require 'time'

class Gigasecond
  def self.from(startDate)
    startDate + (10**9/ #gigasecond
        60/ #seconds per minute
        60/ #minutes per hour
        24) #hours per day
  end
end

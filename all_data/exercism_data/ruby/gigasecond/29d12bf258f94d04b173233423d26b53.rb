# returns a date 1 billion seconds from the input date granular to the day

require 'date'
require 'time'


class Gigasecond
  def self.from (date)
    # Time math is convienient as addition is in seconds, so we convert.
    t=Time.new(date.year, date.month, date.day)+1000000000
    # Return in Date format.
    return Date.new(t.year, t.month, t.day)
  end
end
    

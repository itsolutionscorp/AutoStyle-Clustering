require 'time'
require 'date'
class Gigasecond
  def self.from(arg)
    (arg.to_time + (10**9)).to_date
end
end

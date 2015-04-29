require 'date'

module Gigasecond
  def self.from(d)
     new_d = d + Rational(10**9, 86400) # 86,400 seconds in a day
     Date.new new_d.year, new_d.month, new_d.day # Work around weird bug where above adds 6,400 seconds to the day...
  end
end

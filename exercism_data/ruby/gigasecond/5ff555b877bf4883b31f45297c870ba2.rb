# gigasecond.rb
# author: Ray Wach
# date: 2015-01-05

require 'time'

class Gigasecond

  GIGASECOND = 1000000000 # One billion seconds

  def self.from (date)
    return (date.to_time + GIGASECOND).to_date
  end
end

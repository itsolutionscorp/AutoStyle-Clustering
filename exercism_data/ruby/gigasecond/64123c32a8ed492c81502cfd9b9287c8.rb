require 'date'
require 'time'
module Gigasecond
  def from(date)
    date + (10**9 / (24 * 60 * 60))
  end
end
Gigasecond.extend(Gigasecond)

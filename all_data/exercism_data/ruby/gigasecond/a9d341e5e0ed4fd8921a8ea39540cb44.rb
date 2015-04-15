require 'date'
require 'time'

module Gigasecond
  GigaDays = (10**9 / (24 * 60 * 60))
  def from(date)
    date + GigaDays
  end
end
Gigasecond.extend(Gigasecond)

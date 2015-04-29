require 'date'
require 'time'

module Gigasecond
  GIGA_DAYS = (10**9 / (24 * 60 * 60))
  def from(date)
    date + GIGA_DAYS
  end
end

Gigasecond.extend(Gigasecond)

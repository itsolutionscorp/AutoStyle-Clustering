require 'date'

module Gigasecond
  extend self
  GS = (10**9)
  
  # Public: Calculates a Gigasecond from date
  #
  # date - A DateTime object
  #
  # Returns a Date object with (10**9) seconds added
  def from(date)
    date + GS
  end
  
end

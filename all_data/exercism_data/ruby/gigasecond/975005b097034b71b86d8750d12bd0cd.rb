require 'time'

module Gigasecond
  GIGASECOND = 10**9

  def self.from(date)
    date + GIGASECOND
  end
end

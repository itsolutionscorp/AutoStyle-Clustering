require 'date'

module Gigasecond
  GIGASECOND = 1_000_000_000 / (60 * 60 * 24)
  def self.from date
    date + GIGASECOND
  end
end

require 'date'

module Gigasecond
  GIGASECONDS  = 10**9
  GIGASEC_DAYS = GIGASECONDS/60/60/24

  def self.from(date)
    date + GIGASEC_DAYS
  end
end

# Steffen Parratt (username Neffetski) 13 Nov 2014
# Exercism.io exercise 2, v2 (after reviewing other solutions)

module Gigasecond

  # Return 1 gigasecond anniversary of a given date
  def self.from (date)
    (date.to_time + 10**9).to_date
  end

end

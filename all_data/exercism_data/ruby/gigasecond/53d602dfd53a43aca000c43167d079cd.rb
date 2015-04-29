# Steffen Parratt (username Neffetski) 13 Nov 2014
# Exercism.io exercise 2, version 1

module Gigasecond

  # Return 1 gigasecond anniversary of a given date
  def self.from (date)
    # convert to the given date to seconds (date.to_time.to_i)
    # add 1 billion seconds to that number (+ 10**9)
    # convert that number to time          (Time.at)
    # convert that number back to a date   (.to_date)
    Time.at(date.to_time.to_i + 10**9).to_date
  end

end

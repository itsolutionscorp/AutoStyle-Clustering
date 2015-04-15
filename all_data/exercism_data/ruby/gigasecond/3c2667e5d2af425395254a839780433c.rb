require 'date'
require 'time'

class Gigasecond
  # Returns the date a billion seconds into the future.
  def self.from arg
    (arg.to_datetime + Rational(10**9, 86400)).to_date
  end
end

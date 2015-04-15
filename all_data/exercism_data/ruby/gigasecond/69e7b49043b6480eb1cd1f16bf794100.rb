module Gigasecond
  # One gigasecond = 1,000,000,000 seconds
  # One day = 24 hrs * 60 min * 60 sec = 86400
  # One gigasecond / one day = 11574
  GIGASECONDS_IN_A_DAY = 11574

  def self.from(birthday)
    birthday + Rational(GIGASECONDS_IN_A_DAY)
  end
end

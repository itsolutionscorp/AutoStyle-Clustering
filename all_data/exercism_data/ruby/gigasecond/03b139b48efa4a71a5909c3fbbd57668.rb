require 'active_support/all'

class Gigasecond

  SECONDS_IN_GIGASECOND = 1000000000

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    (@birthdate + SECONDS_IN_GIGASECOND.seconds).to_date
  end

end

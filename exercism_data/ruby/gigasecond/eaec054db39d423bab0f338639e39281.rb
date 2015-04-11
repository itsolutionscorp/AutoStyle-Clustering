class Gigasecond
  attr_reader :birthdate

  GIGASECOND = 10**9
  SECONDS_IN_DAY = 86400
  
  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    birthdate + Rational(GIGASECOND, SECONDS_IN_DAY).to_i
  end
end

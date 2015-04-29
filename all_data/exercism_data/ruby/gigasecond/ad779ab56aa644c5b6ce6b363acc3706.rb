class Gigasecond

  SECONDS_IN_A_DAY = 86400
  ONE_GIGASECOND = 1000000000
  
  def self.in_days
    ONE_GIGASECOND/SECONDS_IN_A_DAY
  end

  attr_reader :birthdate
  def initialize(birthdate)
      @birthdate = birthdate
  end

  def date
    birthdate + Gigasecond.in_days
  end

end

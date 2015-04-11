class Gigasecond
  
  GIGASECOND = 10 ** 9
  NO_SECONDS_IN_A_DAY = 60 * 60 * 24
  GIGASECOND_IN_DAYS = GIGASECOND / NO_SECONDS_IN_A_DAY
  
  def initialize date
    @date = date
  end
  
  def date
    @date + GIGASECOND_IN_DAYS
  end
  
end

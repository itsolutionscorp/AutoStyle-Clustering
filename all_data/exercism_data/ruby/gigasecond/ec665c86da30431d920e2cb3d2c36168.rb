class Gigasecond

  attr_reader :date

  DAYS_PER_GIGASECOND = (10**9)/60/60/24

  def initialize(birthdate)
    @date = birthdate.next_day(DAYS_PER_GIGASECOND)
  end
end

class Gigasecond

  DAYS_PER_GIGASECOND = 11574

  attr_reader :date

  def initialize(birth_date)
    @date = birth_date + DAYS_PER_GIGASECOND
  end

end

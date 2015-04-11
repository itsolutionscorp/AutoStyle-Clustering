class Gigasecond

  def initialize(date)
    @initial_date = date
  end

  DAYS_PER_GIGASECOND = 1000000000 / 60 / 60 / 24

  def date
    @initial_date + DAYS_PER_GIGASECOND
  end

end

class Gigasecond

  # seconds_per_day = 86_400
  # a gigasecond = 1_000_000_000 seconds
  # a gigasecond in days = 1_000_000_000 / 86_400 = 11_574 days
  GIGASECOND_IN_DAYS = 11_574

  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    @birth_date + GIGASECOND_IN_DAYS
  end

end

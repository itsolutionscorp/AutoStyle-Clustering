class Gigasecond
  BILLION_SECONDS_IN_DAYS = 11574

  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    @birth_date + BILLION_SECONDS_IN_DAYS
  end
end

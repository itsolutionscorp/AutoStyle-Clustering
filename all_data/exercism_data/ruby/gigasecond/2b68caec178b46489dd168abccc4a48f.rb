class Gigasecond
  DAYS_IN_GIGASECOND = 1_000_000_000 / (60 * 60 * 24)

  def initialize(date)
    @date = date
  end

  def date
    @date + DAYS_IN_GIGASECOND
  end
end

class Gigasecond
  DAYS_IN_GS = 1_000_000_000 / (60 * 60 * 24)

  def initialize(start_date)
    @start_date = start_date
  end

  def date
    @start_date + DAYS_IN_GS
  end
end

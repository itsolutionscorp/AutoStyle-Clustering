class Gigasecond
  GIGASECOND_IN_DAYS = 1_000_000_000 / (60 * 60 * 24)

  def initialize(start_date)
    @start_date = start_date
  end

  def date
    start_date + GIGASECOND_IN_DAYS
  end

private
  attr_reader :start_date
end

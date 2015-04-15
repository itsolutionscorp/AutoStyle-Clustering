class Gigasecond
  attr_reader :start_date

  GIGASECOND = 10**9
  SECONDS_TO_DAYS_DIVSIOR = 60*60*24

  def initialize(date)
    @start_date = date
  end

  def date
    start_date + gigasecond_as_days
  end

  def gigasecond_as_days
    GIGASECOND / SECONDS_TO_DAYS_DIVSIOR
  end
end

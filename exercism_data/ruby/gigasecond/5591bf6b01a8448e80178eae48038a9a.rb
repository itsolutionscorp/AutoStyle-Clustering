class Gigasecond
  SECONDS = 1_000_000_000
  SECONDS_PER_DAY = 60 * 60 * 24
  DAYS = SECONDS / SECONDS_PER_DAY

  def initialize(date)
    @date = date
  end

  def date
    @date + DAYS
  end
end

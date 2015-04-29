class Gigasecond
  GIGASECOND = 1_000_000_000
  SECONDS_PER_DAY = 60 * 60 * 24
  DAYS_PER_GIGASECOND = GIGASECOND / SECONDS_PER_DAY

  class << self
    def from(date)
      return date + DAYS_PER_GIGASECOND
    end
  end
end

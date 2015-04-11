class Gigasecond
  SECONDS_PER_DAY = 60 * 60 * 24
  DAYS_IN_GIGASECOND = 10**9 / SECONDS_PER_DAY

  class << self
    def from(start_date)
      start_date + DAYS_IN_GIGASECOND
    end
  end
end

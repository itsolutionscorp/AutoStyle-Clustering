class Gigasecond
  GIGASECOND = 10**9
  SECONDS_PER_DAY = 24 * 60 * 60
  DAYS_PER_GIGASECOND = GIGASECOND / SECONDS_PER_DAY

  def self.from(from_date)
    from_date + DAYS_PER_GIGASECOND
  end
end

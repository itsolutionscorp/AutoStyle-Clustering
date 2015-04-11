class Gigasecond
  GS = 10**9
  SECONDS_PER_DAY = 60 * 60 * 24
  DAYS_PER_GIGASECOND = GS / SECONDS_PER_DAY

  def self.from(date)
    date + DAYS_PER_GIGASECOND
  end
end

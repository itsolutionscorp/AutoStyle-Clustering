class Gigasecond

  GIGASECOND = 1_000_000_000
  SECONDS_PER_DAY = 86_400
  DAYS_IN_A_GIGASECOND = GIGASECOND / SECONDS_PER_DAY

  def self.from(start_date)
    start_date + DAYS_IN_A_GIGASECOND
  end

end

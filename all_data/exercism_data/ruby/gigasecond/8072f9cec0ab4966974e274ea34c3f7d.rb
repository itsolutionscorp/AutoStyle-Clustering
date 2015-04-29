class Gigasecond
  SECONDS_IN_A_DAY = 24 * 60 * 60
  DAYS_IN_A_BILLION_SECONDS = 10**9/SECONDS_IN_A_DAY

  def self.from(dt)
    dt + DAYS_IN_A_BILLION_SECONDS
  end
end

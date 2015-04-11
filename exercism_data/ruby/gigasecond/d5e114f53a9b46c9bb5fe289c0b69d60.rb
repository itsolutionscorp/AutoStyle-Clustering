class Gigasecond
  INTERVAL_SECONDS = 10**9
  INTERVAL_DAYS    = INTERVAL_SECONDS / (60 * 60 * 24)

  def self.from(date)
    date + INTERVAL_DAYS
  end
end

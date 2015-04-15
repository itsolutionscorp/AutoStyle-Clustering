class Gigasecond
  SECONDS = (10**9)
  HOURS = SECONDS / 3600
  DAYS = HOURS/ 24

  def self.from(date)
    date + DAYS
  end
end

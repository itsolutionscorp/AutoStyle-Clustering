class Gigasecond
  GS = 1_000_000_000
  SEC_PER_DAY = 86_400
  DAYS_IN_GS = GS / SEC_PER_DAY
  def self.from(date)
    date + DAYS_IN_GS
  end
end

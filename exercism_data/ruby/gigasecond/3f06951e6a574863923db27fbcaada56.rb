class Gigasecond
  ONE_GIGASECOND = 10 ** 9
  SECONDS_PER_DAY = 60 * 60 * 24

  def self.from(date)
    days = ONE_GIGASECOND / SECONDS_PER_DAY
    date + days
  end
end

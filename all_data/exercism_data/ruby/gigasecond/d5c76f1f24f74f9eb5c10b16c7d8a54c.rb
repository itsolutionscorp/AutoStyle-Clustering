class Gigasecond
  ONE_GIGASECOND = 1_000_000_000
  SECS_PER_DAY = 60*60*24

  def self.from(date)
    date + gigasecond_in_days
  end

  def self.gigasecond_in_days
    ONE_GIGASECOND / SECS_PER_DAY
  end
end

class Gigasecond
  SECONDS_PER_DAY = 60*60*24
  GIGASECOND_AS_DAYS = (10**9)/SECONDS_PER_DAY
  def self.from(date)
    date + GIGASECOND_AS_DAYS
  end
end

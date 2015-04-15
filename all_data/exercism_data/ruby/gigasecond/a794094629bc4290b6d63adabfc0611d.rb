class Gigasecond
  SECONDS_PER_DAY = 60*60*24
  def self.from(date)
    date + (10**9)/SECONDS_PER_DAY
  end
end

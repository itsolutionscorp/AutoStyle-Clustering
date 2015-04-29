class Gigasecond
  GIGASECOND = 10**9 # seconds
  GS_IN_DAYS = GIGASECOND / 3600 / 24 # days

  def self.from(date)
    date + GS_IN_DAYS 
  end
end

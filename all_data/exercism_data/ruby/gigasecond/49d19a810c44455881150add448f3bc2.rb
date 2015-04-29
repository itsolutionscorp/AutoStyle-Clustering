class Gigasecond
  GIGASECOND_DAYS = 10**9 / 60 / 60 / 24

  def self.from(date)
    date + GIGASECOND_DAYS
  end
end

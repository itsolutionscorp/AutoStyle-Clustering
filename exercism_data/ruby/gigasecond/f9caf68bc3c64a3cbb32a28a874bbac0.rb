class Gigasecond

  GIGASECOND = 1000000000
  DAYS_OF_GIGASECOND = GIGASECOND / (24*60*60)

  def self.from(f_date)
    f_date + DAYS_OF_GIGASECOND
  end
end

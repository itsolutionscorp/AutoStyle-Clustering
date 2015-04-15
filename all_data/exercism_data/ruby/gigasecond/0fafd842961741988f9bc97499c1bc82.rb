class Gigasecond

  SECONDS_IN_A_DAY = 86_400
  GIGASECOND = 1_000_000_000

  def self.from(date)
    gigasecond_date = date + Rational(GIGASECOND, SECONDS_IN_A_DAY)
    gigasecond_date.to_datetime.to_date
  end
end

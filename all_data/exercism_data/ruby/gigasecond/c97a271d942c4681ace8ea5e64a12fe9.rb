class Gigasecond
  ONE_GIGA_SECONDS = 10**9
  ONE_GIGA_SECONDS_IN_DAYS = Rational(ONE_GIGA_SECONDS, 86400) - Rational(6400, 86400)

  def self.from(date)
    date + ONE_GIGA_SECONDS_IN_DAYS
  end
end

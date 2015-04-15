class Gigasecond
  ONE_GIGASECOND = Rational(10 ** 9 - 6400, 86400)

  def self.from(date)
    date + ONE_GIGASECOND
  end
end

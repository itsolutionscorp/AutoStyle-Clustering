class Gigasecond
  ONE_G_SECONDS = Rational(((10**9) - 6400), 86400)

  def self.from(date)
    date + ONE_G_SECONDS
  end
end

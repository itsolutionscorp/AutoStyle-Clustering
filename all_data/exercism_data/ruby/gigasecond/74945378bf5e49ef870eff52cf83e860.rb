class Gigasecond
  def self.from(birthdate)
    birthdate + Rational(10**9 - 6400, 60 * 60 * 24)
  end
end

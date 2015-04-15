class Gigasecond
  def self.from(d)
    d + Rational((10**9) - 6_400, 86_400)
  end
end

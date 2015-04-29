class Gigasecond
  def self.from(date)
    date + Rational((1000000000-6400), 86400)
  end
end

class Gigasecond
  def self.from date
    date + Rational(10 ** 9, 86400).to_i
  end
end

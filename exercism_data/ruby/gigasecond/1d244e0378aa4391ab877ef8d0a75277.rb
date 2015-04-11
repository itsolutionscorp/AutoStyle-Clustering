class Gigasecond
  def self.from (date)
    change = date + Rational(10**9,86400).floor
  end
end

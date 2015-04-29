class Gigasecond
  def self.from(date)
    gs = Rational(10**9, 86400).to_i
    date + gs
  end
end

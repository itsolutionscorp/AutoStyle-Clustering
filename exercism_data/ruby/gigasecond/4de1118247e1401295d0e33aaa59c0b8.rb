class Gigasecond
  def self.from(d)
    time = DateTime.parse(d.to_s) + Rational(10**9, 86400)
    Date.parse(time.to_s)
  end
end

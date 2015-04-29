class Gigasecond
  def self.from(date)
    future = date + Rational(10**9, 86400)
    Date.new(future.year, future.month, future.day)
  end
end

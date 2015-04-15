class Gigasecond
  def self.from(date)
    date = Date.parse(date.to_s)
    date + (Rational((10**9)-6400, 86400))
  end

  def to_date_time

  end
end

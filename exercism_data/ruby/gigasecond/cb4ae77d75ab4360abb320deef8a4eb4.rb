class Gigasecond
  def self.from(date)
    (date.to_datetime + Rational(10**9, 86400)).to_date
  end
end

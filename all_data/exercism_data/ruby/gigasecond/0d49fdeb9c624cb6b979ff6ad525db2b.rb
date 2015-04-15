class Gigasecond
  def self.from(date)
    gigasecond_date = date + Rational(1_000_000_000, 86400)
    gigasecond_date.to_datetime.to_date
  end
end

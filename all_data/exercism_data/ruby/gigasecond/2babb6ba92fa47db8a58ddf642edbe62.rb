class Gigasecond
  def self.from(start_date)
    plus_gigasecond = start_date.to_datetime + Rational(1000000000,86400)
	return plus_gigasecond.to_date
  end
end

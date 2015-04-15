require 'date'

class Gigasecond
  def self.from(date_time)
    date_time = date_time.to_datetime + Rational(10**9, 86400)
    date_time.to_date
  end
end

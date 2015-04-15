class Gigasecond
  def self.from(start_date)
    if start_date.respond_to?(:sec)
      precise_time = start_date + 10**9
      day = Date.new(precise_time.year, precise_time.month, precise_time.day)
    else
      start_date + Rational(10**9/86400)
    end
  end
end

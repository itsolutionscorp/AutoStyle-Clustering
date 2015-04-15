class Gigasecond
  def self.from(date)
    Date.parse (date + Rational(10**9, 86400)).strftime
  end
end

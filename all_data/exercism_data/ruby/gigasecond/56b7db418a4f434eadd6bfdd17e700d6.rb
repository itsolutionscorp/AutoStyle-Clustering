class Gigasecond
  def self.from (date)
    change = date + Rational(10**9,86400)
    new_date = Date.new(change.year,change.month,change.day)
  end
end

class Gigasecond
  SECONDS = 10**9
  PARTS = { seconds: 1, minutes: 60, hours: 60, days: 24 }
  
  def count(part)
    Rational(SECONDS, seconds_in(part)).to_i
  end
  
  def self.from(date)
    date + new.count(:days)
  end
  
  private
  def seconds_in(part)
    PARTS.values.take(PARTS.keys.index(part).next).inject(:*)
  end
end

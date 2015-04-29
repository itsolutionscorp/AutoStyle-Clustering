class Gigasecond
  def self.from(date)
    (date.to_time + 1.gigasecond).to_date
  end
end

class Numeric
  def gigaseconds
    self * (10**9)
  end
  alias :gigasecond :gigaseconds
end

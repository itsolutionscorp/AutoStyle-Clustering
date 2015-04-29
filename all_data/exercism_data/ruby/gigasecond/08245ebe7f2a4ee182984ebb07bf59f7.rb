class Integer
  def gigaseconds
    self * 1_000_000_000
  end
end

class Gigasecond
  def self.from(time)
    time + 1.gigaseconds
  end
end

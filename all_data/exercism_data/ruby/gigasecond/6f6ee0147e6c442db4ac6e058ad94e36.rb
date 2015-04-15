class Gigasecond
  def self.from(d)
    d + (10**9 / (60 * 60 * 24))
  end
end

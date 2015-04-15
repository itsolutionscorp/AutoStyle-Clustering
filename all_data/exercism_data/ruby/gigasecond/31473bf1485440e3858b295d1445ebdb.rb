class Gigasecond
  def self.gigasecond
    10**9
  end

  def self.from(time)
    time + gigasecond
  end
end

class Gigasecond

  def self.from(startTime)
    Time.at(startTime + (10**9))
  end
end

class Gigasecond
  ONE_GIGASECOND_IN_SECONDS = 10**9

  def self.from(start)
    start.to_time + ONE_GIGASECOND_IN_SECONDS
  end
end

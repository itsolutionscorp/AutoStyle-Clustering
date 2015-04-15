class Gigasecond

  SECONDS_PER_GIGASECOND = 10**9

  def self.from(start_time)
    start_time + SECONDS_PER_GIGASECOND
  end

end

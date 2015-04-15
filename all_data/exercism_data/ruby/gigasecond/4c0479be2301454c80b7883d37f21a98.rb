class Gigasecond
  ONE_GIGASECOND = 10**9

  def self.from(start_time)
    start_time + ONE_GIGASECOND
  end
end

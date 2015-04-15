class Gigasecond
  DURATION_IN_SECONDS = 1_000 * 1_000_000

  def self.from(from_time)
     from_time + DURATION_IN_SECONDS
  end
end

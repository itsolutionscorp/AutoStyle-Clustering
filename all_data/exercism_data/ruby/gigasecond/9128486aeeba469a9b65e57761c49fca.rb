class Gigasecond
  DISTANCE_IN_SEC = 1_000_000_000

  def self.from(starting_time)
    starting_time + DISTANCE_IN_SEC
  end
end

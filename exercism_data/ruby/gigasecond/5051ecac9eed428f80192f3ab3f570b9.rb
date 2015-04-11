class Gigasecond
  DISTANCE_IN_SEC = 1000000000

  def self.from(starting_time)
    starting_time + DISTANCE_IN_SEC
  end
end

class Gigasecond
  GIGASECONDS = 1_00_00_00_000

  def self.from(time)
    time + GIGASECONDS
  end
end

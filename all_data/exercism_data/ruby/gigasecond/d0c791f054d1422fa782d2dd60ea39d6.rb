class Gigasecond
  MILLION = 1_000_000_000

  def self.from(time)
    time + MILLION
  end
end

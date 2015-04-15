class Gigasecond
  GIGA = 1_000_000_000

  def self.from(start_time)
    start_time + GIGA
  end
end

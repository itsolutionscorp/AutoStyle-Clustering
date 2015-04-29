class Gigasecond

  # A gigasecond = 1 billion seconds

  GIGASECOND = 1_000_000_000

  def self.from(time)
    time + GIGASECOND
  end

end

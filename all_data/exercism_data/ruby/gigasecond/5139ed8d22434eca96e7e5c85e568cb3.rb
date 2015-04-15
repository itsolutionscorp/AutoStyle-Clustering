class Gigasecond
  GIGASECOND_IN_SECONDS = 1000000000.freeze

  def self.from(time)
    time + GIGASECOND_IN_SECONDS
  end
end

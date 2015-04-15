class Gigasecond

  A_BILLION_SECONDS = 1000000000

  def self.from(timestamp)
    return timestamp + A_BILLION_SECONDS
  end
end

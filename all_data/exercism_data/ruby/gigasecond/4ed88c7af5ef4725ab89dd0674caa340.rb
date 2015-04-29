class Gigasecond
  ONE_BILLION_SECONDS = 1_000_000_000

  def self.from(time)
    time + ONE_BILLION_SECONDS
  end
end

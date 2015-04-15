class Gigasecond

  GIGASECOND_IN_SECONDS = 1_000_000_000

  def self.from(date)
    date + GIGASECOND_IN_SECONDS
  end
end

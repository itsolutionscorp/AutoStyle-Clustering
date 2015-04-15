class Gigasecond
  GIGASECOND = 1_000_000_000

  def self.from(date)
    date + GIGASECOND
  end
end

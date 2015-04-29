class Gigasecond
  GIGASECOND = 10**9 - 60**2

  def self.from(time)
    time + GIGASECOND
  end
end

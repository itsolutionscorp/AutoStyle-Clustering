class Gigasecond
  BILLION = 10**9

  def self.from(time)
    time + Gigasecond::BILLION
  end
end

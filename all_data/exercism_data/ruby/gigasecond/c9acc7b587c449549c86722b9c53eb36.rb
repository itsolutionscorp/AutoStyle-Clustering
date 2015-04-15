class Gigasecond
  def self.from(start)
    start + Gigasecond.seconds_to_days(10**9)
  end

  private

  def self.seconds_to_days(s)
    s / 60 / 60 / 24
  end
end

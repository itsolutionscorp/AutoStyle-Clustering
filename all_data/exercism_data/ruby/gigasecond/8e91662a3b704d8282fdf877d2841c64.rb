class Gigasecond
  def self.from(date)
    date += seconds_to_days(10**9)
  end
  private
  def self.seconds_to_days(sec)
    sec / (60 * 60 * 24)
  end
end

module Gigasecond
  SecondsInDay = 24 * 60 * 60
  Seconds = 10**9

  def self.from(start)
    start + Seconds / SecondsInDay
  end
end

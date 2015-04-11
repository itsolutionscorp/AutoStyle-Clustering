class Gigasecond
  SECONDS = 10**9

  def self.from(time)
    Time.at(time + SECONDS)
  end
end

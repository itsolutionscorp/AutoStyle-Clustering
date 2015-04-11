class Gigasecond
  def self.from(time)
    Time.at(time.tv_sec + (10**9)).utc
  end
end

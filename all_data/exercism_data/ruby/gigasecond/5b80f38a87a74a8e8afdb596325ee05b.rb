class Gigasecond
  def self.from(time)
    gigasecond = 10**9
    Time.at(time.to_i + gigasecond).utc
  end
end

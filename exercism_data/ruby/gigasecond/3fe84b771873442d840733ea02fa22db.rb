class Gigasecond
  def self.from(time)
    gigaseconds = time.to_i + (10**9)
    Time.at(gigaseconds).utc
  end
end

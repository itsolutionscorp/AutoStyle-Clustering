class Gigasecond
  def self.from(time)
    Time.at(time.to_i + (10**9))
  end
end

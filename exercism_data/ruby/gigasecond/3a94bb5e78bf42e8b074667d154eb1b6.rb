class Gigasecond
  def self.from(time)
    gs = time.to_i + (10**9)
    Time.at(gs).utc
  end
end

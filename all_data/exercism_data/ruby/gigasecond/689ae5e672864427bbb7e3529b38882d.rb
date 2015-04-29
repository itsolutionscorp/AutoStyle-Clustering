class Gigasecond
  def self.from(time)
    gigasecond = 10**9
    Time.at(time + gigasecond)
  end
end

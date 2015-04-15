class Gigasecond
  def self.from(utc)
    Time.at utc + 10**9
  end
end

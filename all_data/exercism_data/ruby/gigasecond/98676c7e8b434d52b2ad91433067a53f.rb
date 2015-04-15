class Gigasecond
  def self.from(timestamp)
    Time.at(timestamp.to_i + 1000000000)
  end
end

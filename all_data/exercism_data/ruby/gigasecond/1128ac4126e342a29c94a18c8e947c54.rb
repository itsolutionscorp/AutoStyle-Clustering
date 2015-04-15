class Gigasecond
  def self.from(date)
    Time.at(date.to_i + 10**9).utc
  end
end

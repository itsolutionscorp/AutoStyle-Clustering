class Gigasecond
  def self.from date
    Time.at(date.to_time.to_i + (10**9))
  end
end

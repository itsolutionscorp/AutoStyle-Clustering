class Gigasecond
  def self.from(time)
    # Convert to seconds, add gigasecond, convert back to time
    Time.at(time.to_i + 10**9)
  end
end

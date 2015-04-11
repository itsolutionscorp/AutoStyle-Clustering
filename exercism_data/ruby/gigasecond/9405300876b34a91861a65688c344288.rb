class Gigasecond
  def self.from(timestamp)
    # Convert any input to Time object
    timestamp = timestamp.to_time if timestamp.respond_to? :to_time

    Time.at(timestamp.to_i + (10**9)).to_date
  end
end

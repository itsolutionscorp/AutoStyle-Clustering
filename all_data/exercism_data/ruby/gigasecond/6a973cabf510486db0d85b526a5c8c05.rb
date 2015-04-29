class Gigasecond
  def self.from(timestamp)
    # Convert any input to Time object
    if timestamp.respond_to? :to_time
      timestamp = timestamp.to_time
    end

    timestamp.to_i

    Time.at(timestamp + (10**9)).to_date
  end
end

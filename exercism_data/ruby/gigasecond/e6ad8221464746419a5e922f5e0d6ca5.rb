class Gigasecond
  # Returns the given time plus one gigasecond.	
  def self.from(time)
    Time.at(time.tv_sec + 10**9)
  end
end

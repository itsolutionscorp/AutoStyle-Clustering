class Gigasecond
  SECONDS = 10**9
  def self.from(utc_t)
    utc_t + SECONDS
  end
end

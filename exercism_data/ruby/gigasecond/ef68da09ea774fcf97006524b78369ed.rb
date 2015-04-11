class Gigasecond
  SECONDS = 10**9
  def self.from(start)
    (start.to_time + SECONDS).to_date
  end
end

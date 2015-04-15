class Gigasecond
  def self.from(date)
    time = date.to_time
    gigasecond_time = time + 1_000_000_000
    gigasecond_time.to_date
  end
end

class Gigasecond
  def self.from(start_date)
    new_time = start_date.to_time + 1 * 10**9
    new_time.to_date
  end
end

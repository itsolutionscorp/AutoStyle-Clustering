class Gigasecond
  def self.from(start_date)
    new_time = start_date.to_time + 1 * 10**9
    Date.new(new_time.year, new_time.month, new_time.day)
  end
end

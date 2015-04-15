class Gigasecond
  def self.from(date)
    time = Time.new(date.year, date.month, date.day)
    new_date = time + 10**9
    Date.new(new_date.year, new_date.month, new_date.day)
  end
end

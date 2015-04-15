class Gigasecond
  def self.from(date)
    time = Time.mktime(date.year, date.month, date.day) + 10**9
    time.to_date
  end
end

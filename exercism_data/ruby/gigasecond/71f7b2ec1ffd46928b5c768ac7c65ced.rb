class Gigasecond
  def self.from(date)
    time = Time.mktime(date.year, date.month, date.day) + 10**9
    Date.new(time.year,time.month,time.day)
  end
end

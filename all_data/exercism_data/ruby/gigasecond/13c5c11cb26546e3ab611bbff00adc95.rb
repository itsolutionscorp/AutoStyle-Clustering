class Gigasecond

  GIGASECOND = 10**9

  def self.from(date)
    time = Time.mktime(date.year, date.month, date.day) + GIGASECOND
    time.to_date
  end
end

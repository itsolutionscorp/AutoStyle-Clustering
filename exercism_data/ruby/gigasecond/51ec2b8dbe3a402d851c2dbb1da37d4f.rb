class Gigasecond
  def self.from(date)
    gigasecond = date.to_time + 10**9
    gigasecond.to_date
  end
end

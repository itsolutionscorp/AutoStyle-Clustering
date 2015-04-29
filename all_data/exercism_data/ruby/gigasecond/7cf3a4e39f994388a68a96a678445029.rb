class Gigasecond
  def self.from(date)
    date = date.to_time + 1000000000
    date.to_date
  end
end

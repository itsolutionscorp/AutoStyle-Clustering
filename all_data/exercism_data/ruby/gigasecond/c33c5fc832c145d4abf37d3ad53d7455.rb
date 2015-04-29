class Gigasecond

  def self.from(date)
    date = date.to_time + 10**9
    date.to_date
  end
end

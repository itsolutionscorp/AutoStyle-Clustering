class Gigasecond

  def self.from(date)
    time = date.to_time + 10**9
    time.to_date
  end

end

class Gigasecond

  def self.from(date)
    date = date.to_time + 10**9
  end
end

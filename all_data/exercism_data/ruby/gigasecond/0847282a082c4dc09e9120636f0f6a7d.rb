class Gigasecond

  def self.from(date)
    days = 10**9 / (24 * 60 * 60)
    date + days
  end

end
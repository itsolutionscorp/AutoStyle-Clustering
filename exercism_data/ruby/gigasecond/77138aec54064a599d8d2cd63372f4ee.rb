class Gigasecond

  def self.from(date)
    days = 10**9 / (24 * 60 * 60)
    date.to_date + days
  end

end

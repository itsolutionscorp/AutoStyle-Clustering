class Gigasecond

  def self.from(date)
    days = in_days(1000000000)
    date + days
  end

  def self.in_days(gigaseconds)
    gigaseconds / 86400
  end

end

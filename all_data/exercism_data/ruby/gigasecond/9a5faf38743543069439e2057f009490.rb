class Gigasecond
  DAYS = 10**9 / 86400

  def self.from(date)
    date + DAYS
  end
end

class Gigasecond
  def self.from(date)
    days = 10**9 / 86_400
    date + days
  end
end

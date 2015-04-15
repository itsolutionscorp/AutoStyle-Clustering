class Gigasecond
  def self.from(date)
    minutes = (10**9) / 60
    hours = minutes / 60
    days = hours / 24
    date + days
  end
end

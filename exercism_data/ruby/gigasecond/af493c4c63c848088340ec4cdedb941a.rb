class Gigasecond
  def self.from(date)
    days = 10**9 / 86400  
    date + days
  end
end

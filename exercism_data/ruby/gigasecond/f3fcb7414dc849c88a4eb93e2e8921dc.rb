class Gigasecond
  def self.from(date)
    DAYS = 11574 # 10**9 / 86_400
    date + DAYS
  end
end

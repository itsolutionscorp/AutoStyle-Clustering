class Gigasecond

  DAYS = 11574 # 1000000000 / (60 * 60 * 24)

  def self.from(date)
    date + DAYS
  end

end

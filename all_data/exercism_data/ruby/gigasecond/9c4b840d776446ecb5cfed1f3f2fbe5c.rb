class Gigasecond

  DAYS_IN_GIGASECOND = 11574
  # 10**9 / (60 * 60 * 24)

  def self.from(date)
    return date + DAYS_IN_GIGASECOND
  end

end

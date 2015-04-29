class Gigasecond
  DAYS_IN_A_GIGASECOND = 11574

  def self.from(date)
    date + DAYS_IN_A_GIGASECOND
  end

end

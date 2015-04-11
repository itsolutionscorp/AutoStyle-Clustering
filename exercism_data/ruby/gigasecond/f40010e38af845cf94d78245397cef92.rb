class Gigasecond

  DAYS_IN_A_GIGASECOND = 11574

  def self.from(date)
    anniversary = date.to_datetime + DAYS_IN_A_GIGASECOND
  end

end

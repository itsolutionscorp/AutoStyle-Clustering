class Gigasecond
  SECONDS_IN_DAY = 86400

  DAYS_IN_GIGASECOND = 1_000_000_000 / SECONDS_IN_DAY

  def self.from(date)
    if date.to_date == date
      date + DAYS_IN_GIGASECOND
    else
      date = date.to_date + 1
      date + DAYS_IN_GIGASECOND
    end
  end
end

class Gigasecond
  GIGASECOND_DAYS = (10**9 / 60.0 / 60.0 / 24.0)

  def self.from(date)
    (date.to_datetime + GIGASECOND_DAYS).to_date
  end
end

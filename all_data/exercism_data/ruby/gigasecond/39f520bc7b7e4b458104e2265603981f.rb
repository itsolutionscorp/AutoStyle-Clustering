class Gigasecond
  GIGASECOND_DAYS = (10**9 / 60.0 / 60.0 / 24.0)

  def self.from(date)
    (DateTime.new(date.year, date.month, date.day) + GIGASECOND_DAYS).to_date
  end
end

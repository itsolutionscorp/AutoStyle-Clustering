class Gigasecond
  GIGA_SECOND = 1_000_000_000

  def self.from(date)
    (date.to_time + GIGA_SECOND).to_date
  end
end

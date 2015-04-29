class Gigasecond
  BILLION = 10**9

  def self.from(date)
    (date.to_time + BILLION).to_date
  end
end

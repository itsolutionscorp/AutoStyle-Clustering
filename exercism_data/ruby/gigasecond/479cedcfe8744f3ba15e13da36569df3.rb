class Gigasecond
  A_BILLION = 1_000_000_000

  def self.from(date)
    (date.to_time + A_BILLION).to_date
  end

end

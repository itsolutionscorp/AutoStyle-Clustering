class Gigasecond

  SECONDS_IN_GIGASECOND = 1_000_000_000

  def self.from(date)
    (date.to_time + SECONDS_IN_GIGASECOND).to_date
  end

end

class Gigasecond
  SECONDS_PER_DAY = (60 * 60 * 24).to_f
  GIGASECONDS = 1_000_000_000.to_f
  def self.from(start_date)
    start_date + (GIGASECONDS / SECONDS_PER_DAY).floor
  end
end

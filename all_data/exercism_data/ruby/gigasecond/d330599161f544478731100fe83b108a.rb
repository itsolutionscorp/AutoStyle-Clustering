class Gigasecond
  SECONDS_PER_GIGASECOND = 1e9
  SECONDS_PER_DAY = 86400

  def self.from(date)
    date + (SECONDS_PER_GIGASECOND / SECONDS_PER_DAY).floor
  end
end

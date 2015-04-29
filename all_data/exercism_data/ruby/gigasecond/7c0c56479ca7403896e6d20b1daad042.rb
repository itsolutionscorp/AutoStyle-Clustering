class Gigasecond
  NO_OF_SECONDS_IN_A_DAY = 24 * 60 * 60
  GIGA_SECOND = 10**9

  def self.from(from_date)
    from_date + GIGA_SECOND/NO_OF_SECONDS_IN_A_DAY
  end
end

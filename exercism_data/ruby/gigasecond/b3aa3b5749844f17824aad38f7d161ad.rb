class Gigasecond
  GIGA_SEC_IN_DAYS = 1_000_000_000 / 60 / 60 / 24

  def self.from(start)
    start + GIGA_SEC_IN_DAYS
  end

end

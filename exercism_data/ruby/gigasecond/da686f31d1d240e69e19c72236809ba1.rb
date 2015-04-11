class Gigasecond

  SECONDS_IN_A_DAY = 60 * 60 * 24
  GIGA_SECONDS = 1_000_000_000

  def initialize date
    @date = date
  end

  def date
    @date + (GIGA_SECONDS / SECONDS_IN_A_DAY)
  end

end

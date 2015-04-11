class Gigasecond

  SECONDS = 10**9
  SECONDS_IN_A_DAY = 60 * 60 * 24
  ONE_GIGASECOND_IN_DAYS = SECONDS / SECONDS_IN_A_DAY

  def initialize(date)
    @date = date
  end

  def date
    @date + ONE_GIGASECOND_IN_DAYS
  end
end

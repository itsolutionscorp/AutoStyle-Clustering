class Gigasecond

  ONE_GIGASECOND_IN_DAYS = 10**9 / (60 * 60 * 24)

  def initialize(date)
    @date = date
  end

  def date
    @date + ONE_GIGASECOND_IN_DAYS
  end
end

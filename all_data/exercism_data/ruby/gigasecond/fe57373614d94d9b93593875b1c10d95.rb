class Gigasecond

  SECOND_IN_DAY = 24 * 60 * 60

  def initialize(date)
    @date = date
  end

  def date
    @date + (10 ** 9 / SECOND_IN_DAY)
  end

end

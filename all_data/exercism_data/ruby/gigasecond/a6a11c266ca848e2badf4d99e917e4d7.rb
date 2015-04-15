class Gigasecond

  SECONDS_IN_DAY = 24 * 60 * 60

  def initialize(base_date)
    @base_date = base_date
  end

  def date
    @base_date + 10 ** 9 / SECONDS_IN_DAY
  end

end

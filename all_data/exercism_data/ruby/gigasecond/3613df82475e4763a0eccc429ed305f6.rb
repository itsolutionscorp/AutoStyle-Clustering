class Gigasecond
  @date
  def initialize(start_date)
    @date = start_date
  end

  def date()
    @date + (10 ** 9) / (3600 * 24)
  end

end

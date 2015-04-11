class Gigasecond
  GIGASECOND_DAYS = 10**9 / 86400

  def initialize(date)
    @date = date
  end

  def date
    @date + GIGASECOND_DAYS
  end
end

class Gigasecond
  DAYS = 11_574

  def initialize(date)
    @date = date
  end

  def date
    @date + DAYS
  end
end

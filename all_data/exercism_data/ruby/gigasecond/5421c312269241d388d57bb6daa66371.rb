class Gigasecond

  DAYS_IN_GIGASECOND = 11_574

  attr_reader :date

  def initialize(date)
    @date = date + DAYS_IN_GIGASECOND
  end
end

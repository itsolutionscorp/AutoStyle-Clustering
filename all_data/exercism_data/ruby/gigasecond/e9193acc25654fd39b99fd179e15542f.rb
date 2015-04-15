class Gigasecond
  attr_reader :date

  DAYS_IN_A_GIGASECOND = 11574

  def initialize(date)
    @date = date + DAYS_IN_A_GIGASECOND
  end
end

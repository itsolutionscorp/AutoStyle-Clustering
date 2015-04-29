class Gigasecond
  ONE_GIGASECOND = 10**9
  SECONDS_IN_A_DAY = (60 * 60 * 24)

  attr_reader :date

  def initialize(date)
    @date = date + one_gigasecond_in_days
  end

  def one_gigasecond_in_days
    ONE_GIGASECOND / SECONDS_IN_A_DAY
  end
end

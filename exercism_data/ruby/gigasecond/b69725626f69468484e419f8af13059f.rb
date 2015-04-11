class Gigasecond
  GIGASECOND = 10**9

  def initialize(date)
    @date = date
  end

  def date
    @date.next_day(second_to_day(GIGASECOND))
  end

  private

  def second_to_day(seconds)
    minutes = seconds / 60
    hours   = minutes / 60
    days    = hours   / 24
  end
end

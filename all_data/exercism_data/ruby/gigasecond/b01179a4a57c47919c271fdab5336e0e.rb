class Gigasecond
  GIGASECOND_IN_DAYS = 11_574  # (10**9) seconds.

  def initialize(start_date)
    @date = start_date
  end

  def date
    @date.next_day(GIGASECOND_IN_DAYS)
  end
end

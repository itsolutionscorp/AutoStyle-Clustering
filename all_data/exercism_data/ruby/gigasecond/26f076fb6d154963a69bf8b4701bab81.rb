module Gigasecond
  DAYS_PER_GIGASECOND = 10**9 / (60 * 60 * 24)
  def Gigasecond.from(start_date)
    start_date + DAYS_PER_GIGASECOND
  end
end

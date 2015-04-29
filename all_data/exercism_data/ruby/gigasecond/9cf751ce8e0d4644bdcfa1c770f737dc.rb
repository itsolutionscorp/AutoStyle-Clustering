class Gigasecond

  # Single Responsibility: add 10^9 seconds to a given date(time)

  GIGASECOND = 10**9
  SECOND_PER_DAY = 24*60*60
  GIGASECOND_IN_DAYS = GIGASECOND / SECOND_PER_DAY

  def self.from(date)
    return date + GIGASECOND_IN_DAYS
  end

end

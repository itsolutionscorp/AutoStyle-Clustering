require 'date'

class Gigasecond
  attr_reader :date

  GIGASECONDS = 1_000_000_000
  SECONDS_IN_A_DAY = 60 * 60 * 24
  DAYS_PER_GIGASECOND = GIGASECONDS / SECONDS_IN_A_DAY

  def initialize(birthdate)
    @date = dateOffset(birthdate, DAYS_PER_GIGASECOND)
  end

  private

  def dateOffset(date, days_offset)
    date_in_julian = date.jd
    Date.jd(date_in_julian + days_offset)
  end
end

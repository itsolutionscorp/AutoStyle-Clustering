require 'date'

class Gigasecond
  attr_reader :birth_date

  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    birth_date + gigasecond_in_days
  end

private

  def gigasecond_in_days
    GIGASECOND / DAY_SECONDS
  end

  DAY_SECONDS = 24 * 60 * 60
  GIGASECOND = 1000000000
end

require 'date'

class Gigasecond
  DAYS_PER_GIGASECOND = 1_000_000_000 / (60 * 60 * 24)

  attr_reader :date

  def initialize(date)
    @date = date + DAYS_PER_GIGASECOND
  end
end

require 'date'

class Gigasecond
  SECONDS_PER_MINUTE = 60
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24

  attr_reader :date

  def initialize(date)
    days = 1_000_000_000/(SECONDS_PER_MINUTE*MINUTES_PER_HOUR*HOURS_PER_DAY)
    @date = date + days
  end
end

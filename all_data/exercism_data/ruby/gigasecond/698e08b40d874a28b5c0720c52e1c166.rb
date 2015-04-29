require 'date'

class Gigasecond
  GIGASECOND  = 1_000_000_000
  SECS_IN_DAY = 86_400

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + GIGASECOND / SECS_IN_DAY
  end
end

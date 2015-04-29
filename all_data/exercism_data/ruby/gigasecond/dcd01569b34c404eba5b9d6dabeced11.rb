require 'date'

class Gigasecond
  GS_IN_DAYS = 10 ** 9 / 3600 / 24

  def initialize date
    @date = date
  end

  def date
    @date + GS_IN_DAYS
  end
end

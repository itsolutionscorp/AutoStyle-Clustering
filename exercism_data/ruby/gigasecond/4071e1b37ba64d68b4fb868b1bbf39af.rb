require 'date'

class Gigasecond
  def initialize(date)
    @date = date
  end
  def date
    @date + ((10**9) / 24 / 60 / 60)
  end
end

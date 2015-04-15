require 'time'
require 'date'

class Gigasecond
  DAYS_IN_GIGASECOND =  11574
  def initialize (date)
    @date = date
  end
  def date
   @date + DAYS_IN_GIGASECOND
  end
end

require 'date'
require 'time'

class Gigasecond

  def initialize(start_date)
    @start_date = start_date
  end

  def giga_to_days
    (10**9)/60/60/24
  end

  def date
    @start_date + giga_to_days
  end

end

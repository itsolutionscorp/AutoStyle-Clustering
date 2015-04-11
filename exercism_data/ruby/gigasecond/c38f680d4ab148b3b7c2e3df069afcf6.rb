require 'date'
require 'time'

class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    (@birth_date.to_time + (10**9)).to_date
  end

end

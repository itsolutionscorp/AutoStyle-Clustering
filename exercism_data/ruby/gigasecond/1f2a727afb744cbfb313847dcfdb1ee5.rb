require 'date'

class Gigasecond

  def initialize(date)
    @date = date
    # Date.new(2011, 4, 25)
  end

  def date
    new_time = @date.to_time + (10**9)
    new_date = new_time.to_date
    new_date
  end
end

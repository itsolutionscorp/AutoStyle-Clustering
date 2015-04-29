require 'date'
require 'time'

class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    # A gigasecond is one billion (10**9) seconds.
    (@date.to_time + (10**9)).to_date
  end
end

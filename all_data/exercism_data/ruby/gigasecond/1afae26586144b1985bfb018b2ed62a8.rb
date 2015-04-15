require 'date'
require 'time'

class Gigasecond
  def initialize(date)
    @date = date
  end
  
  def date
    (@date.to_time + (10**9)).utc.to_date
  end
  
end

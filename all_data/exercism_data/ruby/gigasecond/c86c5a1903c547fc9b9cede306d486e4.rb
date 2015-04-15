require 'date'
require 'time'

class Gigasecond
  GIGASECOND  = 10**9

  def initialize(date)
    @date = date
  end
  
  def date
    (@date.to_time + (GIGASECOND)).utc.to_date
  end
  
end

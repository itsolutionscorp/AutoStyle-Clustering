require 'date'

class Gigasecond
  GSECOND = 10 ** 9
  
  def initialize date
    @date = date
  end

  def date
    (@date.to_time + GSECOND).to_date
  end
end

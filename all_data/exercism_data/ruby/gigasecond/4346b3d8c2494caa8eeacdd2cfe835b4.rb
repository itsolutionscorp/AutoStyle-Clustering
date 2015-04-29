require 'date'
require 'time'

class Gigasecond 

  def initialize(date_of_birth)
    @date = date_of_birth
  end

  def date
    (@date.to_time + 1_000_000_000).to_date
  end
end

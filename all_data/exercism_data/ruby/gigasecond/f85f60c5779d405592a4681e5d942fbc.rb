require 'date'
require 'time'

class Gigasecond

  def initialize(start_date)
    @date_born = start_date.to_time
  end

  def date
    (@date_born + 1_000_000_000).to_date
  end

end

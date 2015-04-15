require 'date'
require 'time'
class Gigasecond
  attr_accessor :date

  def initialize(origin_date)
    @date = origin_date
  end

  def date
    (@date.to_time + 1_000_000_000).utc.to_date
  end
end

require 'date'
require 'time'
class Gigasecond < Struct.new(:from_date)
  def date
    time.to_date
  end

  def time
    from_time + 1_000_000_000
  end

  def from_time
    from_date.to_time
  end
end

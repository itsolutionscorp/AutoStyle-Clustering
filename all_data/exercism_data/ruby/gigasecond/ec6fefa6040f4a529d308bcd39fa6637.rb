require 'delegate'
class Gigasecond
  def initialize(start_date)
    self.start_date = start_date
  end

  def date
    start_date.gigaseconds_since(1)
  end

  private

  attr_accessor :start_date
end

class Date
  SECONDS_PER_DAY = 86400
  def gigaseconds_since(multiple)
    self + (multiple.gigaseconds / SECONDS_PER_DAY)
  end
end

class Time
  def gigaseconds_since(multiple)
    self + multiple.gigaseconds
  end
end

class Numeric
  def gigaseconds
    self * 1_000_000_000
  end
  alias :gigasecond :gigaseconds
end

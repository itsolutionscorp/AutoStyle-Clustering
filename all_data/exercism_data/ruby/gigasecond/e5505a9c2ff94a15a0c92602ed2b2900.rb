require 'delegate'
class Gigasecond < SimpleDelegator
  def date
    __getobj__.gigaseconds_since(1)
  end
end

class DateTime
  def gigaseconds_since(multiple)
    self + multiple.gigaseconds
  end
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

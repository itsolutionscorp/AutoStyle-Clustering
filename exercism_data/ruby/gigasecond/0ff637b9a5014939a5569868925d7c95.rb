require 'delegate'
class Gigasecond < SimpleDelegator
  def date
    __getobj__.gigaseconds_since(1)
  end
end

class DateTime
  def gigaseconds_since(multiple)
    self + ((10**9) * multiple)
  end
end

class Date
  def gigaseconds_since(multiple)
    self + (11574 * multiple)
  end
end

class Time
  def gigaseconds_since(multiple)
    self + ((10**9) * multiple)
  end
end

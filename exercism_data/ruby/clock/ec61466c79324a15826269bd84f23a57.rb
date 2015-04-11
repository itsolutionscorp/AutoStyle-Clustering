class Clock
  class << self
    alias_method :at, :new
  end

  attr_reader :minutes

  def initialize(hour, min = 0)
    @minutes = (hour * 60 + min) % (24 * 60)
  end

  def to_s
    "%02d:%02d" % [hour, min]
  end

  # operation override

  def +(minutes)
    self.class.at(hour, min + minutes)
  end

  def -(minutes)
    self.class.at(hour, min - minutes)
  end

  def ==(other)
    self.minutes == other.minutes
  end

  private

  def hour
    @hour ||= minutes / 60
  end

  def min
    @min ||= minutes % 60
  end
end

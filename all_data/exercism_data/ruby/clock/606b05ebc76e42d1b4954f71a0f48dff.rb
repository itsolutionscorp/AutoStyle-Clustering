# class representing a clock
class Clock
  include Comparable

  MINS_IN_DAY = 1440

  def self.at(hours, minutes = 0)
    new(minutes + hours * 60)
  end

  def initialize(minutes)
    @minutes = minutes
  end

  def to_s
    hours, minutes = (@minutes % MINS_IN_DAY).divmod(60)
    format('%02d:%02d', hours, minutes)
  end

  def +(other)
    self.class.new @minutes + other
  end

  def -(other)
    self.class.new @minutes - other
  end

  def <=>(other)
    to_s <=> other.to_s
  end
end

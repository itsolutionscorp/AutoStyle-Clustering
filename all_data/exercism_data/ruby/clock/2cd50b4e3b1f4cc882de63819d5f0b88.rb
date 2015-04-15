class Clock
  attr_reader :hour, :min

  def self.at(hour, min = 0)
    Clock.new(hour, min)
  end

  def initialize(hour, min)
    @hour = hour
    @min = min
  end

  def to_s
    "%02d:%02d" % [@hour % 24, @min.abs]
  end

  def +(min)
    plus_hour, plus_min = min.divmod(60)
    @hour += plus_hour
    @min += plus_min
    self
  end

  def -(min)
    minus_hour, minus_min = min.divmod(60)
    @hour -= minus_hour
    @hour -= 1 if @min.zero?
    @min -= minus_min
    self
  end

  def ==(other_clock)
    (@hour == other_clock.hour) && (@min == other_clock.min)
  end

  def !=(other_clock)
    (@hour != other_clock.hour) || (@min != other_clock.min)
  end
end

class Clock
  include Comparable

  attr_reader :hour, :minute

  def initialize(hour, minute)
    raise ArgumentError unless (0..23).cover?(hour) or (0..59).cover?(minute)

    @hour   = hour
    @minute = minute
  end

  def +(minutes)
    delta_hours, @minute = (@minute + minutes    ).divmod(60)
              _, @hour   = (@hour   + delta_hours).divmod(24)

    self
  end

  def -(minutes)
    delta_hours, @minute = (@minute - minutes    ).divmod(60)
              _, @hour   = (@hour   + delta_hours).divmod(24)

    self
  end

  def <=>(other_clock)
    return -1 if @hour   < other_clock.hour
    return  1 if @hour   > other_clock.hour
    return -1 if @minute < other_clock.minute
    return  1 if @minute > other_clock.minute
    return  0
  end

  def to_s
    "%02d:%02d" % [@hour, @minute]
  end

  def self.at(hour, minute = 0)
    Clock.new(hour, minute)
  end
end

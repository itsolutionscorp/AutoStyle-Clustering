class Clock
  include Comparable

  attr_reader :hour, :minute

  def initialize(hour, minute)
    raise ArgumentError unless (0..23).cover?(hour) or (0..59).cover?(minute)

    @hour   = hour
    @minute = minute
  end

  def to_s
    "#{hour.to_s.rjust(2, "0")}:#{minute.to_s.rjust(2, "0")}"
  end

  def +(minutes)
    @minute += minutes
    (@hour += 1; @minute -= 60) while @minute >= 60
    (@hour -= 24              ) while @hour   >= 24

    self
  end

  def -(minutes)
    @minute -= minutes
    (@hour -= 1; @minute += 60) while @minute < 0
    (@hour += 24              ) while @hour   < 0

    self
  end

  def <=>(other_clock)
    return -1 if @hour   < other_clock.hour
    return  1 if @hour   > other_clock.hour
    return -1 if @minute < other_clock.minute
    return  1 if @minute > other_clock.minute
    return  0
  end

  def self.at(hour, minute = 0)
    Clock.new(hour, minute)
  end
end

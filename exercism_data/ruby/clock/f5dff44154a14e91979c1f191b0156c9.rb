# class representing a clock
class Clock
  attr_reader :minutes
  MINS_IN_DAY = 1440

  def self.at(hour, minutes = 0)
    Clock.new(minutes + hour * 60)
  end

  def initialize(minutes)
    @minutes = minutes
  end

  def to_s
    mh = minutes_hours @minutes
    "#{clock_print(mh[:hours])}:#{clock_print(mh[:minutes])}"
  end

  def +(other)
    Clock.new @minutes += other
  end

  def -(other)
    Clock.new @minutes -= other
  end

  def ==(other)
    @minutes == other.minutes
  end

  private

  def clock_print(n)
    format '%02d', n
  end

  def minutes_hours(mins)
    min_hour = (mins % MINS_IN_DAY).divmod(60)
    { hours: min_hour[0], minutes: min_hour[1] }
  end
end

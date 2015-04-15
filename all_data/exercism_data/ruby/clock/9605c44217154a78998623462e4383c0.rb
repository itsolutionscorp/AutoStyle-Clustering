class Clock
  HOURS_PER_DAY = 24
  MINUTES_PER_HOUR = 60
  MINUTES_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR

  def self.at(hour, minute = 0)
    new(hour: hour, minute: minute)
  end

  def initialize(hour: 0, minute:)
    @minute = hour * MINUTES_PER_HOUR + minute
  end

  def to_s
    "%02d:%02d" % minute.divmod(MINUTES_PER_HOUR)
  end

  def +(minutes)
    self.class.new(minute: (minute + minutes) % MINUTES_PER_DAY)
  end

  def -(minutes)
    self + -minutes
  end

  def ==(other)
    minute == other.minute
  end

  protected

  attr_reader :minute
end

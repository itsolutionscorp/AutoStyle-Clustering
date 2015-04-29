class Clock
  MINUTES_IN_HOUR = 60
  HOURS_IN_DAY = 24

  attr_reader :hour, :minute

  def initialize(hour, minute)
    @hour = hour
    @minute = minute
  end

  def to_s
    "%02d:%02d" % [@hour, @minute]
  end

  def +(minutes)
    extra_hours, new_minute = (@minute + minutes).divmod(MINUTES_IN_HOUR)
    new_hour = (@hour + extra_hours) % HOURS_IN_DAY
    self.class.new(new_hour, new_minute)
  end

  def -(minutes)
    self + -minutes
  end

  def ==(other)
    hour == other.hour && minute == other.minute
  end

  def self.at(time, minute = 0)
    self.new(time, minute)
  end
end

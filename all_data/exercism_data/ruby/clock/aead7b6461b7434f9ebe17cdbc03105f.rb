class Clock
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24

  attr_reader :hour, :minute

  def initialize(hour, minute = 0)
    additional_hours, @minute = minute.divmod(MINUTES_PER_HOUR)
    @hour = (hour + additional_hours) % HOURS_PER_DAY
  end

  def self.at(*args)
    new(*args)
  end

  def +(minutes)
    self.class.new(@hour, @minute + minutes)
  end

  def -(minutes)
    self.+(-minutes)
  end

  def ==(other)
    @hour == other.hour && @minute == other.minute
  end

  def to_s
    "%02d:%02d" % [@hour, @minute]
  end
end

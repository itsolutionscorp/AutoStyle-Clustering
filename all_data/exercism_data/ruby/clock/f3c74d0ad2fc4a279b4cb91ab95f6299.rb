class Clock
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24

  class << self
    alias_method :at, :new
  end

  def initialize(hour, minutes = 0)
    @hour = hour
    @minutes = minutes
    resolve_time
  end

  def +(minutes)
    self.class.new(@hour, @minutes + minutes)
  end

  def -(minutes)
    self.class.new(@hour, @minutes - minutes)
  end

  def ==(other)
    to_s == other.to_s
  end

  def to_s
    format("%0#{2}d:%0#{2}d", @hour, @minutes)
  end

  private

  def resolve_time
    @hour += (@minutes / MINUTES_PER_HOUR)
    @hour %= HOURS_PER_DAY
    @minutes %= MINUTES_PER_HOUR
  end
end

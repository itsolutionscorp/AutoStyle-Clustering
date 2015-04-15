class Clock
  HOURS_IN_ONE_DAY = 24
  MINUTES_IN_ONE_HOUR = 60

  def self.at(hours, minutes = 0)
    new(hours, minutes)
  end

  def to_s
    format(hours) + ':' + format(minutes)
  end

  def +(minutes)
    @minutes += minutes
    self
  end

  def -(minutes)
    @minutes -= minutes
    self
  end

  def ==(clock)
    to_s == clock.to_s
  end

  private

  def initialize(hours, minutes)
    @minutes = minutes + MINUTES_IN_ONE_HOUR * hours
  end

  def hours
    (@minutes / MINUTES_IN_ONE_HOUR) % HOURS_IN_ONE_DAY
  end

  def minutes
    @minutes % MINUTES_IN_ONE_HOUR
  end

  def format(amount)
    "%02d" % amount
  end
end

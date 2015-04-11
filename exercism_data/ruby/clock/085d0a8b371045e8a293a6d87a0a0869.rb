class Clock

  MINUTES_IN_HOUR  = 60
  HOURS_IN_DAY     = 24
  
  attr_accessor :minutes

  def initialize(minutes=0)
    @minutes = minutes % (HOURS_IN_DAY * MINUTES_IN_HOUR)
  end

  def self.at(hours, minutes=0)
    new((hours * MINUTES_IN_HOUR) + minutes)
  end

  def +(n)
    Clock.new(self.minutes + n)
  end

  def -(n)
    self + -n
  end

  def to_s
    "%02d:%02d" % minutes.divmod(MINUTES_IN_HOUR)
  end

  def ==(another_clock)
    self.minutes == another_clock.minutes
  end

end

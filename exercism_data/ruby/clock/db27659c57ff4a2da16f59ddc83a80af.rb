class Clock

  MINUTES_IN_HOUR  = 60.to_f
  HOURS_IN_DAY     = 24
  
  attr_accessor :hours, :minutes

  def initialize(hours=0, minutes=0)
    @hours   = hours
    @minutes = minutes
    self
  end

  def self.at(hours, minutes=0)
    new(hours, minutes)
  end

  def +(n)
    self.hours   += n / MINUTES_IN_HOUR
    self.minutes += n % MINUTES_IN_HOUR
    normalize_hours
    self
  end

  def -(n)
    self.hours   -= n / MINUTES_IN_HOUR
    self.minutes += n % MINUTES_IN_HOUR
    normalize_hours
    self
  end

  def normalize_hours
    self.hours += HOURS_IN_DAY if self.hours < 0           # -1:00
    self.hours -= self.hours if self.hours >= HOURS_IN_DAY # 24:00
  end

  def to_s
    "#{'%02d' % self.hours}:#{'%02d' % self.minutes}"
  end

  def ==(another_clock)
    self.hours == another_clock.hours && self.minutes == another_clock.minutes
  end

end

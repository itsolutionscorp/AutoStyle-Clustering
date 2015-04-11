class Clock
  def initialize(hours, minutes)
    @minutes = hours*60 + minutes
  end

  def self.at(hours, minutes=0)
    new(hours, minutes)
  end

  def hours
    @minutes / 60 % 24
  end

  def minutes
    @minutes % 60
  end

  def to_s
    "%02d:%02d" % [hours, minutes]
  end

  def +(minutes)
    @minutes += minutes
    self
  end

  def -(minutes)
    @minutes -= minutes
    self
  end

  def ==(other)
    self.hours == other.hours &&
      self.minutes == other.minutes
  end

end

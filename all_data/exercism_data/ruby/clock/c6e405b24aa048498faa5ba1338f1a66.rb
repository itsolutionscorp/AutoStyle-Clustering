class Clock
  def self.at hour, minutes=0
    Clock.new hour * 60 + minutes
  end

  def initialize minutes
    @minutes = minutes
  end

  def to_s
    "%02d:%02d" % hours_and_minutes
  end

  def +(addition)
    self.class.new(@minutes + addition)
  end

  def -(subtraction)
    self.class.new(@minutes - subtraction)
  end

  def ==(other)
    self.hours_and_minutes == other.hours_and_minutes
  end

  protected def hours_and_minutes
    hours, minutes = @minutes.divmod(60)
    [(hours % 24), minutes]
  end
end

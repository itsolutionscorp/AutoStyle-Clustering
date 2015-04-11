class Clock
  def self.at hour, minutes=0
    Clock.new hour * 60 + minutes
  end

  def initialize minutes
    @minutes = minutes
  end

  def to_s
    "%02d:%02d" % hh_mm
  end

  def +(addition)
    Clock.new(@minutes + addition)
  end

  def -(subtraction)
    self + -subtraction
  end

  def ==(other)
    self.hh_mm == other.hh_mm
  end

  protected def hh_mm
    hh, mm = @minutes.divmod(60)
    [(hh % 24), mm]
  end
end

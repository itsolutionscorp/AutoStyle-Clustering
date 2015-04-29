class Clock
  def self.at hour, minutes=0
    Clock.new hour * 60 + minutes
  end

  def initialize minutes
    @minutes = minutes
  end

  def to_s
    hh, mm = @minutes.divmod(60)
    "%02d:%02d" % [(hh % 24), mm]
  end

  def +(addition)
    Clock.new(@minutes + addition)
  end

  def -(addition)
    Clock.new(@minutes - addition)
  end

  def ==(other)
    self.to_s == other.to_s
  end
end

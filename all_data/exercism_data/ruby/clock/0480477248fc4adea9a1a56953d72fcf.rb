class Clock
  def self.at hr, min = 0
    new (hr * 60) + min
  end

  attr_reader :minutes
  protected :minutes

  def initialize minutes
    @minutes = minutes % (24 * 60)
  end

  def + min
    Clock.new @minutes + min
  end

  def - min
    Clock.new @minutes - min
  end

  def == other
    @minutes == other.minutes
  end

  def to_s
    "%02d:%02d" % @minutes.divmod(60)
  end
end

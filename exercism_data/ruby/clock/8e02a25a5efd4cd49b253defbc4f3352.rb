class Clock
  def self.at hr, min = 0
    new (hr * 60) + min
  end

  attr_reader :minutes
  protected :minutes

  def initialize minutes
    @minutes = minutes
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
    "#{pad(hrs)}:#{pad(mins)}"
  end

  private
  def hrs
    hrs = @minutes / 60
    return 0 if hrs > 23
    hrs = 24 + hrs if hrs < 0
    hrs
  end

  def mins
    @minutes % 60
  end

  def pad n
    n.to_s.rjust(2, "0")
  end
end

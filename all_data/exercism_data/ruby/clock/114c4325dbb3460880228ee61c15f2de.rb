class Clock
  def initialize(h, m=0)
    @minutes = h * 60 + m
    refresh
  end

  def refresh
    @minutes %= (24 * 60)
    @h, @m = @minutes.divmod(60)
  end

  def minutes
    @minutes
  end

  def self.at(h, m=0)
    Clock.new(h, m)
  end

  def to_s
    sprintf("%02d:%02d", @h, @m)
  end

  def +(i)
    Clock.new(0, @minutes + i)
  end

  def -(i)
    self.+(-i)
  end
  
  def ==(clock2)
    minutes == clock2.minutes
  end

  protected :minutes
  private :refresh
end

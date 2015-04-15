class Clock

  def initialize(time)
    @time = time
  end

  def self.at(h, m=0)
    Clock.new(Time.new 0, 1, 1, h, m)
  end

  def to_s
    "%02d:%02d" % [ @time.hour, @time.min ]
  end

  def + (secs)
    Clock.new(@time + (secs * 60))
  end

  def - (secs)
    Clock.new(@time - (secs * 60))
  end

  def == (clock)
    to_s == clock.to_s
  end

end

class Clock

  def initialize(time)
    @time = time
  end

  def hour
    @time.hour
  end

  def minute
    @time.min
  end

  def to_s
    "%02d:%02d" % [ hour, minute ]
  end

  def +(seconds)
    self.class.new(@time + seconds * 60)
  end

  def -(seconds)
    self + (seconds * -1)
  end

  def ==(clock)
    to_s == clock.to_s
  end

  def self.at(hour, minute = 0)
    Clock.new(Time.new 0, 1, 1, hour, minute)
  end

end

class Clock

  def initialize(time)
    @time = time
  end

  def minute
    @time.min
  end

  def hour
    @time.hour
  end

  def to_s
    "%02d:%02d" % [hour, minute]
  end

  def +(sec)
    Clock.new(@time + (sec * 60))
  end

  def -(sec)
    Clock.new(@time - (sec * 60))
  end

  def self.at(hour, minute = 0)
    Clock.new(Time.new(0, 1, 1, hour, minute))
  end

  def == (clock)
    clock.to_s == to_s
  end
end

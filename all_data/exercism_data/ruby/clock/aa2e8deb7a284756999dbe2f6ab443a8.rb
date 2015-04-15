class Clock
  def initialize(hours, minutes)
    @time = hours * 60 + minutes
  end

  def self.at(hours, minutes = 0)
    new(hours, minutes)
  end

  def +(minutes)
    @time += minutes
    self
  end

  def -(minutes)
    @time -= minutes
    self
  end

  def ==(other)
    self.to_s == other.to_s
  end

  def to_s
    time = @time % (60 * 24)
    "%02d:%02d" % [time / 60 , time % 60]
  end
end

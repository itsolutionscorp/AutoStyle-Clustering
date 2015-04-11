class Clock
  def self.at(hours, minutes=0)
    time = hours*60 + minutes
    new(time)
  end

  attr_reader :time
  def initialize(time)
    @time = time
  end

  def to_s
    "%02d:%02d" % [hours, minutes]
  end

  def +(minutes)
    Clock.new(time + minutes)
  end

  def -(minutes)
    Clock.new(time - minutes)
  end

  def ==(other)
    to_s == other.to_s
  end

private
  def hours
    time / 60 % 24
  end

  def minutes
    time % 60
  end
end

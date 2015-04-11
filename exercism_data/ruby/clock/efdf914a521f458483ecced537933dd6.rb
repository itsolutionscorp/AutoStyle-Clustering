class Clock
  attr_reader :hours, :minutes

  def initialize(hours, minutes)
    @hours, @minutes = hours, minutes
  end

  def self.at(i, j=0)
    new(i, j)
  end

  def +(i)
    off, m = (minutes + i).divmod(60)
    _, h = (hours + off).divmod(24)
    Clock.new(h, m)
  end

  def -(i)
    self + -i
  end

  def ==(other)
    hours == other.hours && minutes == other.minutes
  end

  def to_s
    hours.to_s.rjust(2,'0') + ':' + minutes.to_s.rjust(2,'0')
  end
end

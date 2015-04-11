class Clock
  
  def self.at(hours, minutes = 0)
    Clock.new(hours,minutes)
  end

  def initialize(hours, minutes)
    @minutes = hours * 60
    @minutes += minutes
  end

  def to_s
    "%02d:%02d" % [@minutes % (24 * 60) / 60 , @minutes % (24 * 60) % 60]
  end

  def +(num)
    @minutes += num
    self
  end

  def -(num)
    @minutes -= num
    self
  end

  def == (other_clock)
    return true if self.to_s == other_clock.to_s
  end
end

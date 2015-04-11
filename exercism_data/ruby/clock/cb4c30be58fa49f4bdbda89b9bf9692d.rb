class Clock
  attr_reader :hour, :minute
  def Clock.at (h, m = 0)
    c = Clock.new(h, m)
  end
  def initialize (h, m)
    @hour = h
    @minute = m
  end
  def to_s
    "%02d:%02d" % [@hour, @minute]
  end
  def + (d)
    m = @minute + d
    h = @hour
    while m >= 60
      m -= 60
      h += 1
    end
    while m < 0
      m += 60
      h -= 1
    end
    h = h % 24
    Clock.at(h, m)
  end
  def - (d)
    self + (-d)
  end
  def == (c)
    @hour == c.hour and @minute == c.minute
  end
end

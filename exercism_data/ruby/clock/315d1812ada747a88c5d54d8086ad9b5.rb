class Clock
  attr_reader :h, :m

  def initialize(h, m=0)
    @h = (h + m / 60) % 24
    @m = m % 60
  end

  def self.at(h, m=0)
    Clock.new(h, m)
  end

  def to_s
    sprintf("%02d:%02d", @h, @m)
  end

  def +(i)
    h2 = @h + i / 60
    m2 = @m + (i % 60)
    h2 = (h2 + (m2 / 60)) % 24
    m2 %= 60
    Clock.new(h2, m2)
  end

  def -(i)
    h2 = @h - i / 60
    m2 = @m - (i % 60)
    while m2 < 0
      m2 += 60
      h2 -= 1
    end
    if h2 < 0
      h2 += 24
    end
    Clock.new(h2, m2)
  end

  def ==(clock2)
    @h == clock2.h and @m == clock2.m
  end
end

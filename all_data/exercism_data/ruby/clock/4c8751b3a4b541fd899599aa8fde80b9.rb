class Clock
  attr_reader :hours, :minutes

  def initialize(h, m)
    @hours = h
    @minutes = m
  end

  def self.at(h=0, m=0)
    Clock.new(h, m)
  end

  def to_s
    h = lambda {|n| n > 9 ? "#{n}" : "0#{n}"}
    m = ->(n) {n > 9 ? "#{n}" : "0#{n}"}
    h.call(@hours) + ":" + m.(@minutes)
  end

  def +(n)
    @hours = (@hours+n/60)%24
    @minutes += n % 60
    return self
  end

  def -(n)
    @hours = (@hours*60-n)/60%24
    @minutes += n % 60
    return self
  end

  def ==(other_clock)
    self.hours == other_clock.hours && self.minutes == other_clock.minutes
  end
end
